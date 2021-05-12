package com.chuang.anarres.office.sys;

import com.chuang.anarres.enums.Language;
import com.chuang.anarres.office.sys.entity.I18n;
import com.chuang.anarres.office.sys.event.I18nUpdatedEvent;
import com.chuang.anarres.office.sys.service.II18nService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service("messageSource")
public class DBMessageSource extends AbstractMessageSource implements ResourceLoaderAware, MessageSource, ApplicationListener<I18nUpdatedEvent> {


    // 这个是用来缓存数据库中获取到的配置的 数据库配置更改的时候可以调用reload方法重新加载
    // 当然 实际使用者也可以不使用这种缓存的方式
    private static final Map<Locale, Map<String, String>> LOCAL_CACHE = new ConcurrentHashMap<>(256);

    @Resource private II18nService i18nService;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        this.reload();
    }

    /**
     * 重新将数据库中的国际化配置加载
     */
    public void reload() {
        LOCAL_CACHE.clear();
        Map<Language, List<I18n>> map  = i18nService.list().stream().collect(Collectors.groupingBy(I18n::getLanguage));

        map.forEach((language, i18ns) -> {
            Map<String, String> cache = LOCAL_CACHE.computeIfAbsent(language.getLocale(), lang -> new HashMap<>());
            i18ns.forEach(i18n -> cache.put(i18n.getI18n(), i18n.getMessage()));
        });
    }


    /**
     * 从缓存中取出国际化配置对应的数据 或者从父级获取
     *
     */
    public String getSourceFromCache(String code, Locale locale) {

        String cache = LOCAL_CACHE.computeIfAbsent(locale, locale1 -> new HashMap<>()).get(code);
        if(null != cache) {
            return cache;
        }

        cache = LOCAL_CACHE.computeIfAbsent(Locale.getDefault(), locale1 -> new HashMap<>()).get(code);
        if(null != cache) {
            return cache;
        }

        if(!locale.equals(Language.zh_CN.getLocale())) {
            cache = LOCAL_CACHE.computeIfAbsent(Language.zh_CN.getLocale(), locale1 -> new HashMap<>()).get(code);
            if (null != cache) {
                return cache;
            }
        }

        try {
            if (null != this.getParentMessageSource()) {
                return this.getParentMessageSource().getMessage(code, null, locale);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return code;
    }




    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        // 空实现，不需要数据源
//        this.getParentMessageSource()
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getSourceFromCache(code, locale);
        return new MessageFormat(msg, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getSourceFromCache(code, locale);
    }

    @Override
    public void onApplicationEvent(I18nUpdatedEvent event) {
        reload();
    }


}
