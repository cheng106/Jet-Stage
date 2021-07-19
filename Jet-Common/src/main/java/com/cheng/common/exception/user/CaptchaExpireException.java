package com.cheng.common.exception.user;

/**
 * 驗證碼失效異常
 */
public class CaptchaExpireException extends UserException {

    private static final long serialVersionUID = 924186947030827985L;

    public CaptchaExpireException() {
        super("user.captcha.expire", null);
    }
}
