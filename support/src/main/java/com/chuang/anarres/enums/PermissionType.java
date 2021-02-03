package com.chuang.anarres.enums;

public enum PermissionType {

    READ_ONLY(0), LICENSABLE(1);

    private final byte code;

    PermissionType(int code) {
        this.code = (byte) code;
    }

    public byte getCode() {
        return code;
    }
}
