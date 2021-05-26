package com.chuang.anarres.service.platform;

import com.chuang.anarres.enums.PlatformType;
import com.chuang.anarres.model.PlatformCheckContent;
import com.chuang.anarres.model.PlatformCookie;
import com.chuang.tauceti.tools.basic.reflect.ClassSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
@Component
public class PlatformManager implements IPushService {

    private static final Map<PlatformType, IPushService> map = new HashMap<>();

    static {
        try {
            Collection<Class<?>> list =
                    ClassSearch.findClass("com.chuang.eden.core.service.platform.impl", false, ClassSearch.and(IPushService.class));

            for (Class<?> clazz : list) {
                IPushService service = (IPushService) clazz.newInstance();
                map.put(service.getPlatformType(), service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<PlatformType> supports() {
       return map.keySet();
    }

    @Override
    public CompletableFuture<Boolean> touch(PlatformCookie cookie) {

        IPushService service = getService(cookie.getPlatformType());

        if(null == service) {
            return CompletableFuture.completedFuture(false);
        } else {
            return service.touch(cookie);
        }
    }

    @Override
    public CompletableFuture<Boolean> addSite(PlatformCookie cookie, PlatformCheckContent pcc, Consumer<PlatformCheckContent> savedPcc) {
        IPushService service = getService(cookie.getPlatformType());

        if(null == service) {
            return CompletableFuture.completedFuture(false);
        } else {
            return service.addSite(cookie, pcc, savedPcc).exceptionally(throwable -> {
                log.error("{} addSite 异常", pcc.getDomain(), throwable);
                return false;
            });
        }
    }

    @Override
    public CompletableFuture<Boolean> linkPush(PlatformCookie cookie, PlatformCheckContent pcc, Collection<String> urls, Consumer<PlatformCheckContent> savedPcc) {
        IPushService service = getService(cookie.getPlatformType());

        if(null == service) {
            return CompletableFuture.completedFuture(false);
        } else {
            return service.linkPush(cookie, pcc, urls, savedPcc).exceptionally(throwable -> {
                log.error("{} linkPush 异常", pcc.getDomain(), throwable);
                return false;
            });
        }
    }

    @Override
    public CompletableFuture<Boolean> sitemapPush(PlatformCookie cookie, PlatformCheckContent pcc) {
        IPushService service = getService(cookie.getPlatformType());

        if(null == service) {
            return CompletableFuture.completedFuture(false);
        } else {
            return service.sitemapPush(cookie, pcc).exceptionally(throwable -> {
                log.error("{} sitemapPush 异常", pcc.getDomain(), throwable);
                return false;
            });
        }
    }

    private IPushService getService(PlatformType type) {
        IPushService service = map.get(type);
        if(null == service) {
            log.warn("{}找不到实现" + type.name());
        }
        return service;
    }

    public PlatformType getPlatformType() {
        return null;
    }

    @Override
    public String referer() {
        return "";
    }
}
