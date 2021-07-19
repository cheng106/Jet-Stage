package com.cheng.system.service;

import com.cheng.common.core.domain.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 根據條件查詢角色列表
     *
     * @param role 角色訊息
     * @return 角色列表
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 根據使用者ID查角色
     *
     * @param userId 使用者ID
     * @return 角色列表
     */
    List<SysRole> selectRolePermissionByUserId(Long userId);

    /**
     * 查詢所有角色
     *
     * @return 角色列表
     */
    List<SysRole> selectRoleAll();

    /**
     * 根據使用者ID取得角色選擇框列表
     *
     * @param userId 使用者ID
     * @return 選中角色ID列表
     */
    List<Integer> selectRoleListByUserId(Long userId);

    /**
     * 根據角色ID查詢角色
     *
     * @param roleId 角色ID
     * @return 角色物件
     */
    SysRole selectRoleById(Long roleId);

    /**
     * 根據使用者ID查詢角色
     *
     * @param userName 使用者名稱
     * @return 角色列表
     */
    List<SysRole> selectRolesByUserName(String userName);

    /**
     * 檢查角色名稱是否有唯一性
     *
     * @param roleName 角色名稱
     * @return 角色訊息
     */
    SysRole checkRoleNameUnique(String roleName);

    /**
     * 檢查角色權限是否有唯一性
     *
     * @param roleKey 角色權限
     * @return 角色訊息
     */
    SysRole checkRoleKeyUnique(String roleKey);

    /**
     * 修改角色訊息
     *
     * @param role 角色訊息
     * @return 修改的筆數
     */
    int updateRole(SysRole role);

    /**
     * 新增角色訊息
     *
     * @param role 角色訊息
     * @return 新增的筆數
     */
    int insertRole(SysRole role);

    /**
     * 根據角色ID刪除角色
     *
     * @param roleId 角色ID
     * @return 刪除的筆數
     */
    int deleteRoleById(Long roleId);

    /**
     * 批次刪除角色
     *
     * @param roleIds 需要刪除的角色ID
     * @return 刪除的筆數
     */
    int deleteRoleByIds(Long[] roleIds);
}
