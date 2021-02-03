package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum UserStatus {
    NORMAL(0), LOCKED(1);

    @EnumValue
    private final byte code;

    UserStatus(int code) {
        this.code = (byte) code;
    }

    public byte getCode() {
        return this.code;
    }
}
