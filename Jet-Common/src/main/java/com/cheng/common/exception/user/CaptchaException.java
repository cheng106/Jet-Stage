package com.cheng.common.exception.user;


/**
 * 驗證碼錯誤異常
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 5150322426907322274L;

    public CaptchaException() {
        super("user.captcha.error", null);
    }
}
