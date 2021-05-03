package com.chuang.anarres.office.sys.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import java.util.EnumSet;
import java.util.Optional;

public enum FieldAction {
    HIDDEN(1), HALF_HIDDEN(2);

    @EnumValue
    private final Integer code;

    FieldAction(Integer code) {
        this.code = code;
    }

    public static Optional<FieldAction> valueOf(Integer code) {
        return EnumSet
                .allOf(FieldAction.class)
                .stream()
                .filter(v -> v.code.equals(code))
                .findFirst();
    }
}
