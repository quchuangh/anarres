package com.chuang.anarres.service.platform.impl;

import com.alibaba.fastjson.JSONObject;
import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.anarres.service.platform.IPushService;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class M360PushService implements IPushService {


    @Override
    public CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc) {
        return addUrl(pcc, cookie)
                .thenCompose(b -> getCode(pcc, cookie))
                .thenCompose(code -> {
                    pcc.setCheckContent(code)
                            .setTemp(code)
                            .setUsername(cookie.getUsername());

                    savedPcc.accept(pcc);
                    return check(pcc, cookie).thenApply(jsonObject -> {
                       pcc.setResult(jsonObject.toJSONString())
                               .setSubmitted(jsonObject.getIntValue("status") == 0);

                       savedPcc.accept(pcc);
                       return pcc.getSubmitted();
                    });
                });
    }

    @Override
    public CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public PlatformType getPlatformType() {
        return PlatformType.M360;
    }

    private CompletableFuture<Boolean> addUrl(PlatformCheckContent pcc, PlatformCookie cookie) {
        return post("http://zhanzhang.so.com/?m=Site&a=add",cookie.getCookie())
                .parameter("site", "www." + pcc.getDomain())
                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject)
                .thenApply(jsonObject -> jsonObject.getIntValue("status") == 0);
    }

    private CompletableFuture<String> getCode(PlatformCheckContent pcc, PlatformCookie cookie) {
        return docGet("http://zhanzhang.so.com/?m=Site&a=get_auth_file&file=www." + pcc.getDomain(), cookie.getCookie())
                .build().asyncExecuteAsString();
    }

//    private CompletableFuture<String> queryUrlId(PlatformCheckContent pcc, PlatformCookie cookie) {
//        return docGet("http://zhanzhang.so.com/?m=Site&a=get_sites&p=1", cookie.getCookie())
//                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject)
//                .thenApply(json -> {
//                   JSONArray array = json.getJSONObject("data").getJSONArray("list");
//                    for (int i = 0; i < array.size(); i++) {
//                        JSONObject obj = array.getJSONObject(i);
//                        return obj.getString("id");
//                    }
//                    throw new SystemWarnException(-1, "无法找到域名id");
//                });
//    }

    private CompletableFuture<JSONObject> check(PlatformCheckContent pcc, PlatformCookie cookie) {
        return ajaxPost("http://zhanzhang.so.com/?m=Site&a=auth", cookie.getCookie())
                .parameter("site", "www." + pcc.getDomain())
                .parameter("auth_method", "html")
                .build().asyncExecuteAsString().thenApply(JSONObject::parseObject);
    }

    public String referer() {
        return "http://zhanzhang.so.com/sitetool/site_manage";
    }
}
