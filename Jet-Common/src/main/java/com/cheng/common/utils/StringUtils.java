package com.cheng.common.utils;

/**
 * 繼承自apache的StringUtils，有另外新增自定義的方法
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String EMPTY_STRING = "";
    private static final char SEPARATOR = '_';

    /**
     * 判斷物件是否為null
     *
     * @param o Object
     * @return boolean <br> true == null <br> false == not null
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * 判斷物件是否不為null
     *
     * @param o Object
     * @return boolean <br> true == not null <br> false == null
     */
    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    /**
     * 判斷字串是否為空字串
     *
     * @param s String
     * @return boolean <br> true == is empty <br> false == is not empty
     */
    public static boolean isEmpty(String s) {
        return isNull(s) || EMPTY_STRING.equals(s.trim());
    }

    /**
     * 判斷字串是否不為空字串
     *
     * @param s String
     * @return boolean <br> true == is not empty <br> false == is empty
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}
