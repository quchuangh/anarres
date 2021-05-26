package com.chuang.anarres.service.platform;

import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.tauceti.httpclient.Request;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface IPushService {

    String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";

    default CompletableFuture<Boolean> touch(PlatformCookie cookie) {
        return docGet(referer(), cookie.getCookie())
                .build().asyncExecuteAsString().thenApply(s -> true);
    }

    CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc);

    CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc);

    CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc);

    PlatformType getPlatformType();

    String referer();


    default Request.Builder ajaxPost(String url, String cookie) {
        return Request.Post(url)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("Referer", referer())
                .header("Cookie", cookie)
                .header("User-Agent", UA);
    }

    default Request.Builder post(String url, String cookie) {
        return Request.Post(url)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Referer", referer())
                .header("Cookie", cookie)
                .header("User-Agent", UA);
    }

    default Request.Builder docGet(String url, String cookie) {
        return Request.Get(url)
                .header("Referer", referer())
                .header("Cookie", cookie)
                .header("User-Agent", UA);
    }

    default String marge(Collection<String> urls, String domain) {
        return urls.stream()
                .map(s -> "https://www." + domain + "/" + s)
                .reduce((s, s2) -> s + "\n" + s2)
                .orElse("");
    }
}
