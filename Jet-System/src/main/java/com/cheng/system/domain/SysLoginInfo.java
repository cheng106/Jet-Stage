package com.cheng.system.domain;

import com.cheng.common.annotation.Excel;
import com.cheng.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysLoginInfo extends BaseEntity {

    private static final long serialVersionUID = 7221411373056480612L;

    @Excel(name = "序號", cellType = Excel.ColumnType.NUMERIC)
    private Long infoId;

    @Excel(name = "使用者帳號")
    private String userName;

    @Excel(name = "登入狀態", readConverterExp = "0:成功, 1:失敗")
    private String status;

    @Excel(name = "登入位置")
    private String ipaddr;

    @Excel(name = "登入地點")
    private String loginLocation;

    @Excel(name = "瀏覽器")
    private String browser;

    @Excel(name = "作業系統")
    private String os;

    @Excel(name = "提示訊息")
    private String msg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "登入時間", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}
