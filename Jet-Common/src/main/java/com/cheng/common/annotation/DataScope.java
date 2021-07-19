package com.cheng.common.annotation;

import java.lang.annotation.*;

/**
 * 資料權限過濾
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部門表的別名
     */
    String deptAlias() default "";

    /**
     * 使用者表的別名
     */
    String userAlias() default "";
}
