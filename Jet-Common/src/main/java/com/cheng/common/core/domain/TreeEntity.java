package com.cheng.common.core.domain;

import java.util.ArrayList;
import java.util.List;

public class TreeEntity extends BaseEntity {

    private static final long serialVersionUID = 8917982922987103157L;
    /**
     * 父選單
     **/
    private String parentName;

    /**
     * 父選單ID
     **/
    private Long parentId;

    /**
     * 顯示順序
     **/
    private Integer orderNum;

    /**
     * 祖級列表
     **/
    private String ancestors;

    /**
     * 子部門
     **/
    private final List<?> children = new ArrayList<>();
}
