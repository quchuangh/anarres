package com.chuang.anarres.office.sys.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by Davy on 2016/2/2.
 * 验证码错误
 */

public class CaptchaException extends AuthenticationException {


    public CaptchaException() {
        super();
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
