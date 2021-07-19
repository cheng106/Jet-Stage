package com.cheng.system.domain;

import com.cheng.common.annotation.Excel;
import com.cheng.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 行為日誌紀錄
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOperationLog extends BaseEntity {
    private static final long serialVersionUID = -3590145919801944314L;

    @Excel(name = "行為日誌主鍵", cellType = Excel.ColumnType.NUMERIC)
    private Long operationId;

    @Excel(name = "模組標題")
    private String title;

    @Excel(name = "業務類型", readConverterExp = "" +
            "0:其它, " +
            "1:新增, " +
            "2:修改, " +
            "3:刪除, " +
            "4:授權, " +
            "5:匯出, " +
            "6:匯入, " +
            "7:強制登出, " +
            "8:產生程式碼, " +
            "9:清空資料")
    private Integer businessType;

    /**
     * 業務類型陣列
     */
    private Integer[] businessTypes;

    @Excel(name = "方法名稱")
    private String method;

    @Excel(name = "請求方式")
    private String requestMethod;

    @Excel(name = "行為類型", readConverterExp = "0:其它, 1:後台使用者, 2:手機端使用者")
    private Integer operationType;

    @Excel(name = "操作人員")
    private String operationName;

    @Excel(name = "部門名稱")
    private String deptName;

    @Excel(name = "請求URL")
    private String operationUrl;

    @Excel(name = "訪問的IP")
    private String operationIp;

    @Excel(name = "操作行為的地點")
    private String operationLocation;

    @Excel(name = "請求參數")
    private String operationParam;

    @Excel(name = "返回的結果")
    private String jsonResult;

    @Excel(name = "狀態", readConverterExp = "0:正常, 1:異常")
    private Integer status;

    @Excel(name = "錯誤訊息")
    private String errorMsg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "操作時間", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;
}
