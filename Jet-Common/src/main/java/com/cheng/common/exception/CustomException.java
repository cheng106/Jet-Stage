package com.cheng.common.exception;

public class CustomException extends BaseException {
    private static final long serialVersionUID = -8656802979453675242L;


    public CustomException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }

    public CustomException(String module, String code, Object[] args) {
        super(module, code, args);
    }

    public CustomException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public CustomException(String code, Object[] args) {
        super(code, args);
    }

    public CustomException(String defaultMessage) {
        super(defaultMessage);
    }
}
