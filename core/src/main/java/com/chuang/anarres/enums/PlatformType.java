package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum  PlatformType {
    SHENMA(1), BAIDU(2), GOOGLE(3), SOGOU(4), M360(5), TOU_TIAO(6);

    @EnumValue
    private final byte code;

    PlatformType(int code){
        this.code = (byte) code;
    }

    public byte getCode() {
        return code;
    }
}
