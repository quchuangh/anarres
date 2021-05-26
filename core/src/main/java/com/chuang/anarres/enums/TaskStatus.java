package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum TaskStatus {
    NONE(10),
    CREATED(0),
    WAITING(1),
    RUNNING(2),
    INTERRUPTED(3),
    ENDED(4),
    ERROR(5);

    TaskStatus(int code) {
        this.code = (byte)code;
    }

    @EnumValue
    private final byte code;

    public byte getCode() {
        return code;
    }
}
