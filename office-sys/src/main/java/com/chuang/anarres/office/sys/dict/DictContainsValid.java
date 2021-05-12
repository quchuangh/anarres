package com.chuang.anarres.office.sys.dict;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DictContainsValid implements ConstraintValidator<Dict, Byte> {

    @Resource private DictManager manager;

    protected String dictType;

    @Override
    public void initialize(Dict maxValue) {
        this.dictType = maxValue.dictType();
    }

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext constraintValidatorContext) {
        return manager.contains(dictType, value);
    }
}
