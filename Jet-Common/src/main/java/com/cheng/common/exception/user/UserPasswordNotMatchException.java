package com.cheng.common.exception.user;

/**
 * 使用者密碼不正確或不符合規範異常
 */
public class UserPasswordNotMatchException extends UserException {

    private static final long serialVersionUID = 7102522563112177746L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
