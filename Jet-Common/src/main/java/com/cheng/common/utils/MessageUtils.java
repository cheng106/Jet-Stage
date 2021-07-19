package com.cheng.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 取得i18n資源
 */
public class MessageUtils {

    /**
     * 根據代號和參數，取得訊息並委託給spring messageSource
     *
     * @param code 代號
     * @param args 參數
     * @return 取得國際化翻譯值
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
