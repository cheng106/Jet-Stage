package com.cheng.system.mapper;

import com.cheng.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper {
    /**
     * 查使用者列表
     *
     * @param sysUser 使用者訊息
     * @return 使用者訊息列表
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 查詢已分配角色列表
     *
     * @param user 使用者訊息
     * @return 使用者訊息列表
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 查詢未分配角色列表
     *
     * @param user 使用者訊息
     * @return 使用者訊息列表
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 根據使用者名稱查詢
     *
     * @param userName 使用者名稱
     * @return 使用者物件
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 根據使用者ID查詢
     *
     * @param userId 使用者ID
     * @return 使用者物件
     */
    SysUser selectUserById(Long userId);

    /**
     * 新增使用者訊息
     *
     * @param user 使用者訊息
     * @return 新增的筆數
     */
    int insertUser(SysUser user);

    /**
     * 修改使用者訊息
     *
     * @param user 使用者訊息
     * @return 修改的筆數
     */
    int updateUser(SysUser user);

    /**
     * 修改使用者大頭照
     *
     * @param userName 使用者名稱
     * @param avatar   大頭照地址
     * @return 修改的筆數
     */
    int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    /**
     * 重設使用者密碼
     *
     * @param userName 使用者名稱
     * @param password 密碼
     * @return 修改的筆數
     */
    int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根據使用者ID删除使用者
     *
     * @param userId 使用者ID
     * @return 刪除的筆數
     */
    int deleteUserById(Long userId);

    /**
     * 批次删除使用者訊息
     *
     * @param userIds 要删除的使用者ID
     * @return 刪除的筆數
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 檢查使用者名稱是否有唯一性
     *
     * @param userName 使用者名稱
     * @return 结果
     */
    int checkUserNameUnique(String userName);

    /**
     * 檢查手機號碼是否有唯一性
     *
     * @param phoneNumber 手機號碼
     * @return 使用者訊息
     */
    SysUser checkPhoneUnique(String phoneNumber);

    /**
     * 檢查Email是否有唯一性
     *
     * @param email 使用者信箱
     * @return 使用者訊息
     */
    SysUser checkEmailUnique(String email);
}
