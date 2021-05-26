package com.chuang.anarres.exception;

import com.chuang.tauceti.support.exception.BusinessException;

public class NohaveArticleException extends BusinessException {
    public NohaveArticleException(int code, String msg) {
        super(code, msg);
    }
}
