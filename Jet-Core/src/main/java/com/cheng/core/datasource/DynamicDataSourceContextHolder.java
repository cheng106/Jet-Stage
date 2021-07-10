package com.cheng.core.datasource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynamicDataSourceContextHolder {

    /**
     * 使用ThreadLocal維護變數，ThreadLocal為每個使用該變數的執行緒提供獨立的變數副本，
     * 所以每一個執行緒都可以獨立的改變自己的副本，而不會影響到其它執行緒所對應的副本
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 設定資料連接來源的變數
     */
    public static void setDataSourceType(String dataSourceType) {
        log.info("切換到{}資料連接來源", dataSourceType);
        CONTEXT_HOLDER.set(dataSourceType);
    }

    /**
     * 取得資料連接來源的變數
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除資料連接來源的變數
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

}
