package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.I18n;
import com.chuang.anarres.enums.I18nType;
import com.chuang.anarres.enums.Language;
import com.chuang.tauceti.tools.basic.HashKit;
import com.chuang.urras.rowquery.IRowQueryService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 国际化  服务类
 * </p>
 *
 * @author chuang
 * @since 2020-12-21
 */
public interface II18nService extends IRowQueryService<I18n> {

    default Optional<I18n> findOne(I18nType type, String i18n, Language language) {
        String md5 = HashKit.md5(type + ":" + i18n + ":" + language);
        return lambdaQuery().eq(I18n::getMd5, md5).eq(I18n::getLanguage, language).oneOpt();
    }


    boolean save(I18nType type, String i18n, String message, Language language);

    String md5(I18nType type, String i18n, Language language);

    boolean delete(I18nType type, String i18n);

    boolean delete(I18nType type, String i18n, Language language);



}
