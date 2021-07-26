package com.cheng.common.core.domain.response.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class ErrorVo {
    private String code;
    private String message;
}
