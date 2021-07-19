package com.cheng.common.core.domain.model;

import lombok.Data;

/**
 * 使用者登入物件
 **/
@Data
public class LoginBody {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
