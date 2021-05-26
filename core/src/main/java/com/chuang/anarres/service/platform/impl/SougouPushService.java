package com.chuang.anarres.service.platform.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.anarres.service.platform.IPushService;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class SougouPushService implements IPushService {

    @Override
    public CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc) {
        CompletableFuture<Boolean> added = addDomain(pcc.getDomain(), cookie.getCookie());
        return added.thenCompose(aBoolean -> domainID(pcc.getDomain(), cookie.getCookie()))
                .thenCompose(id -> {
                    return getCheckById(cookie.getCookie(), id)
                            .thenApply(md5 -> {
                                pcc.setCheckContent(md5);
                                pcc.setUsername(cookie.getUsername());
                                pcc.setTemp(id);
                                savedPcc.accept(pcc);
                                return md5;
                            })
                            .thenCompose(s -> check(cookie.getCookie(), id))
                            .thenApply(jsonObject -> {
                               boolean success = jsonObject.getBoolean("success");
                               pcc.setSubmitted(success)
                                       .setResult(jsonObject.toJSONString());
                               savedPcc.accept(pcc);
                               return success;
                            });

                });
    }

    @Override
    public CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc) {
//        String body = marge(urls, pcc.getDomain());
//        return ajaxPost("", cookie.getCookie())
//                .parameter("mainid", pcc.getTemp())
//                .parameter("subdomain", body)
//                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject)
//                .thenApply(json -> json.getBoolean("success"));
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public PlatformType getPlatformType() {
        return PlatformType.SOGOU;
    }



    private CompletableFuture<Boolean> addDomain(String domain, String cookie) {
        int mon= CollectionKit.randomOne(-1, -2, -3);
        LocalDateTime time = LocalDateTime.now().plusMonths(mon);
        return ajaxPost("http://zhanzhang.sogou.com/index.php/dashboard/create", cookie)
                .parameter("website", "https://www." + domain)
                .parameter("site_year", time.getYear() +"")
                .parameter("site_month", time.getMonth() + "")
                .build()
                .asyncExecuteAsString()
                .thenApply(JSONObject::parseObject)
                .thenApply(json -> json.getBoolean("success"));
    }

    private CompletableFuture<String> domainID(String domain, String cookie) {

        return ajaxPost("http://zhanzhang.sogou.com/index.php/dashboard/view",  cookie)
                .parameter("status", "notverify")
                .parameter("page", "1")
                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject)
                .thenApply(json -> {
                    if(json.getBoolean("success")) {
                        JSONArray array = json.getJSONArray("model");
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            String site = obj.getString("site_address");
                            if(site.equalsIgnoreCase("www." + domain)) {
                                return obj.getString("id");
                            }
                        }

                        throw new BusinessException(-1, "找不到，找不到，太难了");
                    } else {
                        throw new BusinessException(-1, "无法获取");
                    }
                });
    }

    private CompletableFuture<String> getCheckById(String cookie, String id) {
        return docGet("http://zhanzhang.sogou.com/index.php/dashboard/getVerifyFile/id/" + id,  cookie)
                .build().asyncExecuteAsString();
    }

    private CompletableFuture<JSONObject> check(String cookie, String id) {
        return ajaxPost("http://zhanzhang.sogou.com/index.php/dashboard/verify", cookie)
                .parameter("siteId", id)
                .parameter("type", "file")
                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject);
    }

    @Override
    public String referer() {
        return "http://zhanzhang.sogou.com/index.php/dashboard/index";
    }

}
