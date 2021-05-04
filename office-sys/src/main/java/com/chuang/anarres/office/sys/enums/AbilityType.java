package com.chuang.anarres.office.sys.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import java.util.EnumSet;
import java.util.Optional;

public enum AbilityType {
    MENU(1), FUNCTION(2), FIELD(3);

    @EnumValue
    private final Integer code;

    AbilityType(Integer code) {
        this.code = code;
    }

    public static Optional<AbilityType> valueOf(Integer code) {
        return EnumSet
                .allOf(AbilityType.class)
                .stream()
                .filter(v -> v.code.equals(code))
                .findFirst();
    }
}
