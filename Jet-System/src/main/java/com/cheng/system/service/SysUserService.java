package com.cheng.system.service;

import com.cheng.common.core.domain.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**
     * 根據查詢條件分頁顯示使用者列表
     *
     * @param user 使用者訊息
     * @return 使用者資訊列表
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 根據查詢條件分頁顯示已分配使用者角色列表
     *
     * @param user 使用者訊息
     * @return 使用者資訊列表
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根據查詢條件分頁顯示未分配使用者角色列表
     *
     * @param user 使用者訊息
     * @return 使用者資訊列表
     */
    List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通過使用者名稱查詢
     *
     * @param userName 使用者名稱
     * @return 使用者訊息
     */
    SysUser selectUserByUserName(String userName);

    /**
     * 通過使用者ID查詢
     *
     * @param userId 使用者ID
     * @return 使用者对象訊息
     */
    SysUser selectUserById(Long userId);

    /**
     * 根據使用者ID查詢使用者所屬的角色群組
     *
     * @param userName 使用者名稱
     * @return 所屬的角色群組
     */
    String selectUserRoleGroup(String userName);

    /**
     * 根據使用者ID查詢使用者所屬的崗位群組
     *
     * @param userName 使用者名稱
     * @return 所屬的崗位群組
     */
    String selectUserPostGroup(String userName);

    /**
     * 檢查使用者名稱是否有唯一性
     *
     * @param userName 使用者名稱
     * @return 是則有唯一性，否則相反
     */
    String checkUserNameUnique(String userName);

    /**
     * 檢查手機號碼是否有唯一性
     *
     * @param user 使用者訊息
     * @return 是則有唯一性，否則相反
     */
    String checkPhoneUnique(SysUser user);

    /**
     * 檢查Email是否有唯一性
     *
     * @param user 使用者訊息
     * @return 是則有唯一性，否則相反
     */
    String checkEmailUnique(SysUser user);

    /**
     * 檢查使用者是否允許訪問
     *
     * @param user 使用者訊息
     */
    void checkUserAllowed(SysUser user);

    /**
     * 新增使用者訊息
     *
     * @param user 使用者訊息
     * @return 成功的筆數
     */
    int insertUser(SysUser user);

    /**
     * 修改使用者訊息
     *
     * @param user 使用者訊息
     * @return 成功的筆數
     */
    int updateUser(SysUser user);

    /**
     * 使用者授權角色
     *
     * @param userId  使用者ID
     * @param roleIds 角色ID陣列
     */
    void insertUserAuth(Long userId, Long[] roleIds);

    /**
     * 修改使用者狀態
     *
     * @param user 使用者訊息
     * @return 成功的筆數
     */
    int updateUserStatus(SysUser user);

    /**
     * 修改使用者基本訊息
     *
     * @param user 使用者訊息
     * @return 成功的筆數
     */
    int updateUserProfile(SysUser user);

    /**
     * 修改使用者大頭照
     *
     * @param userName 使用者名稱
     * @param avatar   大頭照圖片位置
     * @return 是否成功
     */
    boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重設使用者密碼
     *
     * @param user 使用者訊息
     * @return 是否重設
     */
    int resetPwd(SysUser user);

    /**
     * 重設使用者密碼
     *
     * @param userName 使用者名稱
     * @param password 密碼
     * @return 是否重設
     */
    int resetUserPwd(String userName, String password);

    /**
     * 使用ID刪除使用者
     *
     * @param userId 使用者ID
     * @return 是否刪除
     */
    int deleteUserById(Long userId);

    /**
     * 批次刪除使用者訊息
     *
     * @param userIds 需要刪除的使用者ID陣列
     * @return 刪除的筆數
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 匯入使用者資料
     *
     * @param userList        使用者資訊列表
     * @param isUpdateSupport 是否支援更新，如果已存在則進行更新資料
     * @param operName        操作使用者
     * @return 结果
     */
    String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName);
}
