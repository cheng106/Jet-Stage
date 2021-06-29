package com.cheng.common.core.domain.entity;

import com.cheng.common.annotation.Excel;
import com.cheng.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 系統角色
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {
    private static final long serialVersionUID = 3077952629265261496L;

    /**
     * 角色ID
     */
    @Excel(name = "角色序號", cellType = Excel.ColumnType.NUMERIC)
    private Long roleId;

    /**
     * 角色名稱
     */
    @Excel(name = "角色名稱")
    private String roleName;

    /**
     * 角色權限
     */
    @Excel(name = "角色權限")
    private String roleKey;

    /**
     * 角色順序
     */
    @Excel(name = "角色順序")
    private String roleSort;

    /**
     * 資料範圍(1:全部資料權限, 2:自定義資料權限, 3:本部門資料權限, 4:本部門及子部門資料權限, 5:僅本人資料權限)
     */
    @Excel(name = "資料範圍", readConverterExp = "1:全部資料權限, 2:自定義資料權限, 3:本部門資料權限, 4:本部門及子部門資料權限, 5:僅本人資料權限")
    private String dataScope;

    /**
     * 選單樹選擇項是否關連顯示 (0:父子不相互關連顯示, 1:父子互相關連顯示)
     */
    private boolean menuCheckStrictly;

    /**
     * 部門樹選擇項是否關連顯示 (0:父子不相互關連顯示, 1:父子互相關連顯示)
     */
    private boolean deptCheckStrictly;

    /**
     * 角色狀態 (0:正常, 1:停用)
     */
    @Excel(name = "角色狀態", readConverterExp = "0:正常, 1:停用")
    private String status;

    /**
     * 刪除標誌 (0:存在, 2:刪除)
     */
    private String delFlag;

    /**
     * 使用者是否存在此角色標誌，預設不存在
     */
    private boolean flag = false;

    /**
     * 選單ID群組
     */
    private Long[] menuIds;

    /**
     * 部門群組 (資料權限)
     */
    private Long[] deptIds;

    @NotBlank(message = "請輸入角色名稱")
    @Size(min = 0, max = 30, message = "角色名稱長度不能超過30個字")
    public String getRoleName() {
        return roleName;
    }

    @NotBlank(message = "請輸入權限")
    @Size(min = 0, max = 100, message = "權限字串長度不能超過100個字")
    public String getRoleKey() {
        return roleKey;
    }

    @NotBlank(message = "請輸入顯示順序")
    public String getRoleSort() {
        return roleSort;
    }
}
