package com.chuang.anarres.crud.service;

import com.chuang.anarres.crud.entity.I18n;
import com.chuang.anarres.crud.enums.I18nType;
import com.chuang.anarres.crud.enums.Language;
import com.chuang.tauceti.rowquery.IRowQueryService;
import com.chuang.tauceti.tools.basic.HashKit;

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
