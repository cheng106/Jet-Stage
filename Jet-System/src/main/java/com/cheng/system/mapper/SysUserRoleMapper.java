package com.cheng.system.mapper;

import com.cheng.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 使用者與角色關連表
 */

public interface SysUserRoleMapper {
    /**
     * 通過ID刪除使用者和角色關連
     *
     * @param userId 使用者ID
     * @return 刪除的筆數
     */
    int deleteUserRoleByUserId(Long userId);

    /**
     * 批次刪除使用者和角色關連
     *
     * @param ids 要刪除的資訊ID
     * @return 刪除的筆數
     */
    int deleteUserRole(Long[] ids);

    /**
     * 根據角色ID查詢角色使用數量
     *
     * @param roleId 角色ID
     * @return 查詢的筆數
     */
    int countUserRoleByRoleId(Long roleId);

    /**
     * 批次新增使用者角色訊息
     *
     * @param userRoleList 使用者角色列表
     * @return 新增的筆數
     */
    int batchUserRole(List<SysUserRole> userRoleList);

    /**
     * 刪除使用者和角色關連訊息
     *
     * @param userRole 使用者和角色關連訊息
     * @return 刪除的筆數
     */
    int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * 批次取消授權使用者角色
     *
     * @param roleId  角色ID
     * @param userIds 要刪除的使用者ID
     * @return 刪除的筆數
     */
    int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
