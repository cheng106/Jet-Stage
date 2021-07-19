package com.cheng.common.constant;

/**
 * 常量訊息
 **/
public class Constants {
    public static final String UTF8 = "UTF-8";

    /**
     * 成功代號
     */
    public static final String SUCCESS = "0";

    /**
     * 失敗代號
     */
    public static final String FAIL = "1";

    /**
     * Token前綴
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Token前綴
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 登入帳號 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 驗證碼 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登入成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 登入失敗
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 登出
     */
    public static final String LOGOUT = "Logout";
}
