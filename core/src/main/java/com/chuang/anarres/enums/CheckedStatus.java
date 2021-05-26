package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum CheckedStatus {
    NO_CHECK(0), PASS(1), REFUSE(2), HIDDEN(3);

    CheckedStatus(int code) {
        this.code = (byte)code;
    }

    @EnumValue
    private final byte code;

    public byte getCode() {
        return code;
    }

    public boolean isDone() {
        return this == PASS || this == REFUSE;
    }
}
