package com.cheng.common.core.domain.entity;

import com.cheng.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 部門
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseEntity {
    private static final long serialVersionUID = 5653194401057873829L;

    /**
     * 部門ID
     */
    private Long deptId;

    /**
     * 父部門ID
     */
    private Long parentId;

    /**
     * 祖級列表
     */
    private String ancestors;

    /**
     * 部門名稱
     */
    private String deptName;

    /**
     * 顯示順序
     */
    private String orderNum;

    /**
     * 部門負責人
     */
    private String leader;

    /**
     * 聯絡電話
     */
    private String phone;

    /**
     * 電子信箱
     */
    private String email;

    /**
     * 部門狀態:0正常, 1停用
     */
    private String status;

    /**
     * 刪除標誌:0存在, 2刪除
     */
    private String delFlag;

    /**
     * 父部門名稱
     */
    private String parentName;

    /**
     * 子部門
     */
    private List<SysDept> children = new ArrayList<>();

    @NotBlank(message = "請輸入部門名稱")
    @Size(max = 30, message = "部門名稱長度請勿超過30個字")
    public String getDeptName() {
        return deptName;
    }

    @NotBlank(message = "請輸入顯示順序")
    public String getOrderNum() {
        return orderNum;
    }

    @Size(max = 10, message = "聯絡電話請勿超過10個數字")
    public String getPhone() {
        return phone;
    }

    @Email(message = "請輸入正確的信箱格式")
    @Size(max = 50, message = "信箱長度不能超過50個字")
    public String getEmail() {
        return email;
    }
}
