package com.cheng.common.core.domain.response;

import com.cheng.common.core.domain.response.vo.ErrorVo;
import com.cheng.common.enums.ErrorType;
import com.cheng.common.exception.BaseException;
import lombok.Getter;

public class ErrorResponse implements BaseResponse {
    @Getter
    private final String code;

    @Getter
    private final String message;

    @Getter
    private final boolean success;

    @Getter
    private ErrorVo error;

    private ErrorResponse(Throwable e) {
        ErrorType err = ErrorType.UnhandledRejection;
        code = err.name();
        message = err.getMessage();
        success = false;
        error = new ErrorVo()
                .setCode(e.getClass().getSimpleName())
                .setMessage(e.getMessage());
    }

    private ErrorResponse(BaseException e) {
        code = e.getCode();
        message = e.getMessage();
        success = false;
        error.setMessage(e.getMessage());
    }

    public static ErrorResponse create(Throwable e) {
        return new ErrorResponse(e);
    }

    public static ErrorResponse create(BaseException e) {
        return new ErrorResponse(e);
    }

}
