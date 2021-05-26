package com.chuang.anarres.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum ProposalStatus {

    CREATED(0),
    RECEIPT(1),
    PASS(2),
    REJECT(3);

    ProposalStatus(int code) {
        this.code = (byte)code;
    }

    @EnumValue
    private final byte code;

    public byte getCode() {
        return code;
    }
}
