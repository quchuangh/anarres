package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum DomainType {

    NEWS(1),
    PHYSICAL(2),
    GAMING(3),
    OTHERS(4)
    ;

    DomainType(int code) {
        this.code = (byte)code;
    }

    public static DomainType valueOf(byte code) {
        if(code < 1 || code > DomainType.values().length) {
            return null;
        }
        code -= 1;
        return DomainType.values()[code];
    }

    @EnumValue
    private final byte code;

    public byte getCode() {
        return code;
    }
}
