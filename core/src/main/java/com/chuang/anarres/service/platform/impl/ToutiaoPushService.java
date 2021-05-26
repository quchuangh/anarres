package com.chuang.anarres.service.platform.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.anarres.service.platform.IPushService;
import com.chuang.tauceti.httpclient.Request;
import com.chuang.tauceti.tools.basic.StringKit;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
public class ToutiaoPushService implements IPushService {

    private String log(String s) {
        log.info(s);
        return s;
    }
    @Override
    public CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc) {
        return queryOrAdd(pcc, cookie).thenApply(id -> {
                    log.info("发现id: " + id);
                    pcc.setTemp(id)
                            .setUsername(cookie.getUsername());
                    savedPcc.accept(pcc);
                    return id;
                })
                .thenCompose(id -> content(id, cookie.getCookie())
                .thenCompose(content -> {
                    pcc.setCheckContent(content);
                    savedPcc.accept(pcc);
                    savedPcc.accept(pcc);
                    return check(pcc.getTemp(), cookie.getCookie());
                })
                .thenApply(jsonObject -> {
                    int code = jsonObject.getIntValue("code");
                    pcc.setResult(jsonObject.toJSONString())
                            .setSubmitted(code == 0);
                    savedPcc.accept(pcc);
                    return pcc.getSubmitted();
                }));

    }

    @Override
    public CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc) {
        String links = urls.stream()
                .map(s -> "\"https://www." + pcc.getDomain() + "/" + s + "\"")
                .reduce((s, s2) -> s + "," + s2)
                .orElse("");
        return Request.Post("https://zhanzhang.toutiao.com/webmaster/api/link/create")
                .header("cookie", cookie.getCookie())
                .header("content-type", "application/json")
                .header("referer", "https://zhanzhang.toutiao.com/page/inner/site/add")
                .header("origin", "https://zhanzhang.toutiao.com")
                .header(":authority", "zhanzhang.toutiao.com")
                .header(":method", "POST")
                .header(":path", "/webmaster/api/link/create")
                .header("user-agent", UA)
                .body("{\"site_id\":" + pcc.getTemp() + ",\"urls\":[" + links + "],\"frequency\":86400,\"clientInfo\":{\"screen_width\":1920,\"screen_height\":1080,\"cookie_enabled\":true,\"browser_language\":\"zh-CN\",\"browser_platform\":\"Win32\",\"browser_name\":\"Mozilla\",\"browser_version\":\"5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36\",\"browser_online\":true},\"submitMethods\":\"url\"}")
                .build().asyncExecuteAsString().thenApply(this::log).thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> {
                    pcc.setLinkPublished(jsonObject.getIntValue("code") == 0);
                    savedPcc.accept(pcc);
                    return jsonObject.getIntValue("code") == 0;
                });
    }

    @Override
    public CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc) {
        return Request.Post("https://zhanzhang.toutiao.com/webmaster/api/link/create")
                .header("cookie", cookie.getCookie())
                .header("content-type", "application/json")
                .header("referer", "https://zhanzhang.toutiao.com/page/inner/site/add")
                .header("origin", "https://zhanzhang.toutiao.com")
                .header(":authority", "zhanzhang.toutiao.com")
                .header(":method", "POST")
                .header(":path", "/webmaster/api/link/create")
                .header("user-agent", UA)
                .body("{\"site_id\":" + pcc.getTemp() + ",\"urls\":[\"https://www." + pcc.getDomain() + "/sitemap.xml\"],\"frequency\":86400,\"clientInfo\":{\"screen_width\":1920,\"screen_height\":1080,\"cookie_enabled\":true,\"browser_language\":\"zh-CN\",\"browser_platform\":\"Win32\",\"browser_name\":\"Mozilla\",\"browser_version\":\"5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36\",\"browser_online\":true},\"submitMethods\":\"sitemap\"}")
                .build().asyncExecuteAsString().thenApply(this::log).thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> jsonObject.getIntValue("code") == 0);
    }

    @Override
    public PlatformType getPlatformType() {
        return PlatformType.TOU_TIAO;
    }

    @Override
    public String referer() {
        return "https://zhanzhang.toutiao.com/page/inner/site/manage";
    }

    private CompletableFuture<JSONObject> check(String id, String cookie) {
        return ajaxPost("https://zhanzhang.toutiao.com/webmaster/api/site/verify", cookie)
                .header("Content-Type", "application/json")
                .body("{\"id\":"+ id +",\"verify_type\":0}")
                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject);
    }

    private CompletableFuture<String> addAndGetId(PlatformCheckContent pcc, PlatformCookie cookie) {
        return Request.Post("https://zhanzhang.toutiao.com/webmaster/api/site/create")
                .header("cookie", cookie.getCookie())
                .header("content-type", "application/json")
                .header("referer", "https://zhanzhang.toutiao.com/page/inner/site/add")
                .header("origin", "https://zhanzhang.toutiao.com")
                .header(":authority", "zhanzhang.toutiao.com")
                .header(":method", "POST")
                .header(":path", "/webmaster/api/site/create")
                .header("user-agent", UA)
                .body("{\"host\":\"www." + pcc.getDomain() + "\",\"protocol\":\"https\",\"clientInfo\":{\"screen_width\":1920,\"screen_height\":1080,\"cookie_enabled\":true,\"browser_language\":\"zh-CN\",\"browser_platform\":\"Win32\",\"browser_name\":\"Mozilla\",\"browser_version\":\"" + UA + "\",\"browser_online\":true}}")
                .build().asyncExecuteAsString().thenApply(this::log).thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> jsonObject.getString("id"));
    }

    private CompletableFuture<String> queryOrAdd(PlatformCheckContent pcc, PlatformCookie cookie) {
        if(StringKit.isNotBlank(pcc.getTemp())) {
            return CompletableFuture.completedFuture(pcc.getTemp());
        }

        return docGet("https://zhanzhang.toutiao.com/webmaster/api/site/list?offset=0&limit=1000&_t=" + System.currentTimeMillis(), cookie.getCookie())
                .build().asyncExecuteAsString().thenApply(this::log).thenApply(JSONObject::parseObject)
                .thenCompose(json -> {
                    JSONArray array = json.getJSONArray("data");
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        if(pcc.getDomain().equalsIgnoreCase(obj.getString("domain").trim())) {
                            return CompletableFuture.completedFuture(obj.getString("id"));
                        }
                    }
                    return addAndGetId(pcc, cookie);
                });
    }

    private CompletableFuture<String> content(String code, String cookie) {
        return docGet("https://zhanzhang.toutiao.com/webmaster/api/site/downloadverify?id=" + code, cookie)
                .build().asyncExecuteAsString().thenApply(this::log);
    }
}
