package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import java.util.Locale;
import java.util.Optional;

/**
 * 语言，事实上 Locale 已经有标准了。
 * 以这种方式来使用，主要还是为了限制本项目中的语种。
 * Created by ath on 2016/7/2.
 */
public enum Language {
    /** 中文简体 */
    zh_CN(1,  Locale.SIMPLIFIED_CHINESE),
    /** 中文繁体 */
    zh_TW(2,  Locale.TRADITIONAL_CHINESE),
    /** US */
    en_US(3, Locale.US),

    /** 韩国 */
    ko_KR(4, Locale.KOREA),
    /** 希腊 */
    el_GR(5, new Locale("el", "GR")),
    /** 波兰 */
    pl_PL(6, new Locale("pl", "PL")),
    /** 土耳其 */
    tr_TR(7, new Locale("tr", "TR"))
    ;

    @EnumValue
    private final byte code;

    private final Locale locale;

    Language(int code, Locale locale) {
        this.code = (byte)code;
        this.locale = locale;
    }

    public static Language valueOf(byte code) {
        if(code < 1 || code > Language.values().length) {
            code = 1;
        }
        code -= 1;
        return Language.values()[code];
    }


    public byte getCode() {
        return code;
    }

    public Locale getLocale() {
        return locale;
    }


    public static Optional<Language> parse(String lang) {
        try {
            return Optional.of(Language.valueOf(lang.replaceAll("-", "_")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
