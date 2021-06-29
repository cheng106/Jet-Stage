package com.cheng.common.core.domain.entity;

import com.cheng.common.annotation.Excel;
import com.cheng.common.annotation.Excels;
import com.cheng.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 系統使用者
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -5785822888203026439L;

    /**
     * 使用者ID
     **/
    @Excel(name = "使用者序號", cellType = Excel.ColumnType.NUMERIC, prompt = "使用者編號")
    private Long userId;

    /**
     * 部門ID
     */
    @Excel(name = "部門編號", type = Excel.Type.IMPORT)
    private Long deptId;

    /**
     * 使用者帳號
     */
    @Excel(name = "登入名稱")
    private String userName;

    /**
     * 使用者暱稱
     */
    @Excel(name = "使用者名稱")
    private String nickName;

    /**
     * 使用者信箱
     */
    @Excel(name = "使用者信箱")
    private String email;

    /**
     * 手機號碼
     */
    @Excel(name = "手機號碼")
    private String phoneNumber;

    /**
     * 使用者性別
     */
    @Excel(name = "使用者性別", readConverterExp = "0=女,1=男,2=未知")
    private String gender;

    /**
     * 使用者大頭照
     */
    private String avatar;

    /**
     * 密碼
     **/
    private String password;

    /**
     * 鹽(加密)
     **/
    private String salt;

    /**
     * 帳號狀態（0正常 1停用）
     **/
    @Excel(name = "帳號狀態", readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 刪除標誌（0:存在 2:刪除）
     */
    private String delFlag;

    /**
     * 最後登入IP
     */
    @Excel(name = "最後登入IP", type = Excel.Type.EXPORT)
    private String loginIp;

    /**
     * 最後登入時間
     */
    @Excel(name = "最後登入時間", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date loginDate;

    /**
     * 部門物件
     */
    @Excels({
            @Excel(name = "部門名稱", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部門負責人", targetAttr = "leader", type = Excel.Type.EXPORT)
    })
    private SysDept dept;

    /**
     * 角色物件
     */
    private List<SysRole> roles;

    /**
     * 角色ID群組
     */
    private Long[] roleIds;

    /**
     * 崗位ID群組
     */
    private Long[] postIds;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    @Size(max = 30, message = "使用者暱稱長度不能超過30個字")
    public String getNickName() {
        return nickName;
    }

    @NotBlank(message = "請輸入使用者帳號")
    @Size(max = 30, message = "使用者帳號長度不能超過30個字")
    public String getUserName() {
        return userName;
    }

    @Email(message = "請輸入正確的信箱格式")
    @Size(max = 50, message = "信箱長度不能超過50個字")
    public String getEmail() {
        return email;
    }

    @Size(max = 10, message = "手機號碼長度不能超過10個字")
    public String getPhoneNumber() {
        return phoneNumber;
    }


    @JsonIgnore
    @JsonProperty
    public String getPassword() {
        return password;
    }

}
