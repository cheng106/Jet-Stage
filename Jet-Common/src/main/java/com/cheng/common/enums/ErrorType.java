package com.cheng.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {
    UnhandledRejection("UnhandledRejectionError", "未知錯誤"),

    ;

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorType(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    ErrorType(String code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
