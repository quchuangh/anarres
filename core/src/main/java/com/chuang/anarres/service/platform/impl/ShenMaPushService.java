package com.chuang.anarres.service.platform.impl;

import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.anarres.service.platform.IPushService;
import com.chuang.tauceti.httpclient.Request;
import com.chuang.tauceti.tools.basic.RegexKit;
import com.chuang.tauceti.tools.basic.StringKit;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
public class ShenMaPushService implements IPushService {

    @Override
    public CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc) {
        return fastCode(pcc, cookie.getCookie()).thenCompose(code -> getContentById(cookie.getCookie(), code).thenCompose(content -> {
                    pcc.setCheckContent(content);
                    pcc.setTemp(code);
                    pcc.setUsername(cookie.getUsername());
                    savedPcc.accept(pcc);
                    return check(cookie.getCookie(), code);
                }))
                .thenApply(s -> {
                    log.info("site:" + s);
                    String str = RegexKit.get(".*(\\{.*})", s, 1);
                    JSONObject json = JSONObject.parseObject(str);
                    pcc.setResult(str)
                            .setSubmitted(json.getIntValue("code") != 1);
                    savedPcc.accept(pcc);
                    return pcc.getSubmitted();
                });
    }

    @Override
    public PlatformType getPlatformType() {
        return PlatformType.SHENMA;
    }

    @Override
    public String referer() {
        return "https://zhanzhang.sm.cn/open/detialPage";
    }

    @Override
    public CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc) {
        String body = marge(urls, pcc.getDomain());

        return authToken(pcc, cookie.getCookie(), savedPcc).thenCompose(token -> {
            String url = "https://data.zhanzhang.sm.cn/push?site=www." + pcc.getDomain() + "&user_name=" + cookie.getUsername() + "&resource_name=mip_add&token=" + token;
            log.info("MIP 推荐内容如下:\n{}\n{}", url, body);
            return Request.Post(url)
                    .body(body)
                    .build()
                    .asyncExecuteAsString()
                    .thenApply(JSONObject::parseObject)
                    .thenApply(jsonObject -> {
                        log.info("MIP 推送结果如:" + jsonObject.toJSONString());
                        pcc.setLinkPublished(jsonObject.getIntValue("returnCode") == 200);
                        savedPcc.accept(pcc);
                        return jsonObject.getIntValue("returnCode") == 200;
                    });
        });
    }


    public CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc) {
        String url = "https://www." + pcc.getDomain() + "/sitemap.xml";
        log.info("sitemap: {}, {}", url, pcc.getTemp());
        return ajaxPost("https://zhanzhang.sm.cn/open/addSitemapUrl", cookie.getCookie())
                .parameter("domain_id", pcc.getTemp())
                .parameter("type", "0")
                .parameter("url", url)
                .build()
                .asyncExecuteAsString()
                .thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> {
                   log.info("{}: sitemap 结果 :{}", pcc.getDomain(), jsonObject.toJSONString());
                   return jsonObject.getIntValue("code") == 0;
                });
    }


    private CompletableFuture<String> authToken(PlatformCheckContent pcc, String cookie, Consumer<PlatformCheckContent> savePcc) {
        if(StringKit.isNotBlank(pcc.getTemp2())) {
            return CompletableFuture.completedFuture(pcc.getTemp2());
        }
        return fastCode(pcc, cookie).thenCompose(code -> docGet("https://zhanzhang.sm.cn/open/mip/id/" + code, cookie).build().asyncExecuteAsString().thenCompose(html -> {
            Document doc = Jsoup.parse(html);
            String authKey = doc.select(".authkey-wrapper .authkey").text();
            if (StringKit.isBlank(authKey)) {
                return updatePushToken(code, cookie).thenApply(token -> {
                   pcc.setTemp2(token);
                   savePcc.accept(pcc);
                   return token;
                });
            } else {
                pcc.setTemp2(authKey);
                savePcc.accept(pcc);
                return CompletableFuture.completedFuture(authKey);
            }
        }));

    }

    private CompletableFuture<String> updatePushToken(String code, String cookie) {
        return ajaxPost("https://zhanzhang.sm.cn/open/UpPushToken", cookie)
                .parameter("id", code)
                .build()
                .asyncExecuteAsString()
                .thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> jsonObject.getString("msg"));
    }


    private CompletableFuture<String> code(String cookie, String domain) {
        return home(cookie).thenCompose(html -> {
            Document doc = Jsoup.parse(html);
            String code = findCodeByDomain(doc, domain);
            if (null != code) {
                return CompletableFuture.completedFuture(code);
            } else {
                String token = doc.select("input[name=csrf_token]").val();
                return submitAndGetCode(cookie, token, domain);
            }
        });
    }

    private CompletableFuture<String> home(String cookie) {
        return docGet("https://zhanzhang.sm.cn/open/detialPage", cookie).build().asyncExecuteAsString();
    }

    private CompletableFuture<String> getContentById(String cookie, String code) {
        return docGet("https://zhanzhang.sm.cn/open/downloadValidateFile/id/" + code, cookie).build().asyncExecuteAsString();
    }

    private String findCodeByDomain(Document doc, String domain) {
        Element node = doc.select("a:contains(" + domain + ")").first();
        if(null == node) {
            return null;
        }
        String href = node.parent().parent().select("p.no-watch a").attr("href");
        return href.substring(href.lastIndexOf("/") + 1);
    }
    private CompletableFuture<String> submitAndGetCode(String cookie, String token, String domain) {
        return ajaxPost("https://zhanzhang.sm.cn/open/addDomain?callback=jQuery110208228570686528449_1607752695628", cookie)
                .parameter("domain", domain)
                .parameter("type", "26")
                .parameter("csrf_token", token)
                .build().asyncExecuteAsString()
                .thenApply(s -> {
                    JSONObject json = JSONObject.parseObject(RegexKit.get(".*(\\{.*})", s, 1));
                    return json.getString("msg");
                });
    }

    private CompletableFuture<String> check(String cookie, String code) {
        return ajaxPost("https://zhanzhang.sm.cn/open/domainCheck?callback=jQuery110208228570686528449_1607752695628", cookie)
                .header("referer", "https://zhanzhang.sm.cn/open/webCheck/web/" + code)
                .parameter("domain_id", code)
                .parameter("type", "2")
                .build()
                .asyncExecuteAsString();
    }

    private CompletableFuture<String> fastCode(PlatformCheckContent pcc, String cookie) {
        return code(cookie, "www." + pcc.getDomain());
    }

}
