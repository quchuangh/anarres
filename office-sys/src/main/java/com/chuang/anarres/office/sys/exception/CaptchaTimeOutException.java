package com.chuang.anarres.office.sys.exception;

/**
 * Created by Davy on 2016/2/2.
 */

public class CaptchaTimeOutException extends CaptchaException {

    public CaptchaTimeOutException() {
        super();
    }

    public CaptchaTimeOutException(String message) {
        super(message);
    }

    public CaptchaTimeOutException(Throwable cause) {
        super(cause);
    }

    public CaptchaTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
