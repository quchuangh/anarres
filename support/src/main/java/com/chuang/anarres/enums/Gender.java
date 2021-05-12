package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Gender {
    MALE(0), FEMALE(1), LADY_BOY(2);

    @EnumValue
    private final byte code;

    Gender(int code) {
        this.code = (byte) code;
    }

    public byte getCode() {
        return this.code;
    }
}
