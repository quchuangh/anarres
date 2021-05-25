package com.chuang.anarres.crud.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum  RoleType {
    USER_ROLE(0),
    ORG_ROLE(1),
    POS_ROLE(2);

    @EnumValue
    private final byte code;

    RoleType(int code) {
        this.code = (byte) code;
    }

    public int getCode() {
        return code;
    }
}
