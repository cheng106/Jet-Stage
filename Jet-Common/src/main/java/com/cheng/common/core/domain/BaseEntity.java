package com.cheng.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 其他的Entity繼承於此類
 **/
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 3381085773905433026L;

    /**
     * 搜尋值
     **/
    private String searchValue;

    /**
     * 建立者
     **/
    private String createUser;

    /**
     * 建立時間
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     **/
    private String updateUser;

    /**
     * 更新時間
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 備註
     **/
    private String remark;

    /**
     * 請求參數
     **/
    private Map<String, Object> params;

}
