package com.cheng.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * @description: 自定義匯出Excel欄位註解
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    /**
     * 匯出時在Excel中的排序
     **/
    int sort() default Integer.MAX_VALUE;

    /**
     * 匯出時在Excel中的名子
     **/
    String name() default "";

    /**
     * 日期格式：如yyyy-MM-dd
     **/
    String dateFormat() default "";

    /**
     * 如果為字典類型，請設定字典的type值 (如: sys_user_gender)
     **/
    String dictType() default "";

    /**
     * 讀取內容時轉成表達式 (如: 1=男，0=女，2=未知)
     **/
    String readConverterExp() default "";

    /**
     * 分隔符號，讀取字串陣列的內容
     **/
    String separator() default ",";

    /**
     * BigDecimal精度，預設:-1 (預設不使用BigDecimal格式化)
     **/
    int scale() default -1;

    /**
     * BigDecimal 小數規則，預設:BigDecimal.ROUND_HALF_EVEN
     **/
    int roundingMode() default BigDecimal.ROUND_HALF_EVEN;

    /**
     * 匯出類型:0=數字，1=字串）
     */
    ColumnType cellType() default ColumnType.STRING;

    /**
     * 匯出時在Excel中每個列的高度
     */
    double height() default 14;

    /**
     * 匯出時在Excel中每個列的寬度
     */
    double width() default 16;

    /**
     * 文字後綴名稱，如:% 90 變成90%
     */
    String suffix() default "";

    /**
     * 當值為空時，欄位的默認值
     */
    String defaultValue() default "";

    /**
     * 提示訊息
     */
    String prompt() default "";

    /**
     * 設定只能選擇不能輸入的列內容
     */
    String[] combo() default {};

    /**
     * 是否匯出資料，應對需求:有時候我們需要匯出一份樣版，就是標題需要但內容需要使用者自己填寫
     */
    boolean isExport() default true;

    /**
     * 另一個類中的屬性名稱，支援多層取得，以小數點隔開
     */
    String targetAttr() default "";

    /**
     * 是否自動統計資料，在最後追加一行統計資料總和
     */
    boolean isStatistics() default false;

    /**
     * 匯出時欄位對齊方式（0:預設，1:靠左，2:至中，3:靠右）
     */
    Align align() default Align.AUTO;

    enum Align {
        AUTO(0), LEFT(1), CENTER(2), RIGHT(3);
        private final int value;

        Align(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 欄位類型（0：匯出匯入，1:僅匯出，2:僅匯入）
     */
    Type type() default Type.ALL;

    enum Type {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    enum ColumnType {
        NUMERIC(0), STRING(1), IMAGE(2);
        private final int value;

        ColumnType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
