package com.cheng.system.domain;

import com.cheng.common.annotation.Excel;
import com.cheng.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 5034379177128313512L;

    @Excel(name = "崗位序號", cellType = Excel.ColumnType.NUMERIC)
    private Long postId;

    @Excel(name = "崗位編號")
    private String postCode;

    @Excel(name = "崗位名稱")
    private String postName;

    @Excel(name = "崗位順序")
    private String postSort;

    @Excel(name = "狀態", readConverterExp = "0=正常, 1=停用")
    private String status;

    /**
     * 使用者是否存在此崗位標誌，預設不存在
     */
    private boolean flag = false;

    @NotBlank(message = "請輸入崗位編號")
    @Size(max = 64, message = "崗位編號長度不能超過64字元")
    public String getPostCode() {
        return postCode;
    }

    @NotBlank(message = "請輸入崗位名稱")
    @Size(max = 50, message = "崗位名稱不能超過50個字")
    public String getPostName() {
        return postName;
    }

    @NotBlank(message = "請輸入顯示順序")
    public String getPostSort() {
        return postSort;
    }
}
