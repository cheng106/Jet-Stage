package com.cheng.common.exception.user;

import com.cheng.common.exception.BaseException;

public class UserException extends BaseException {

    private static final long serialVersionUID = -1370536909689260883L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
