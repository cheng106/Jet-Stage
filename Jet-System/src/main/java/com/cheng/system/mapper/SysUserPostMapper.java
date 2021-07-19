package com.cheng.system.mapper;

import com.cheng.system.domain.SysUserPost;

import java.util.List;

/**
 * 使用者和崗位關連表
 */

public interface SysUserPostMapper {
    /**
     * 根據使用者ID刪除使用者和崗位的關連
     *
     * @param userId 使用者ID
     * @return 刪除的筆數
     */
    int deleteUserPostByUserId(Long userId);

    /**
     * 根據崗位ID查詢崗位使用數量
     *
     * @param postId 崗位ID
     * @return 查詢的筆數
     */
    int countUserPostById(Long postId);

    /**
     * 批次刪除使用者和崗位關連
     *
     * @param ids 要刪除的ID
     * @return 刪除的筆數
     */
    int deleteUserPost(Long[] ids);

    /**
     * 批次新增使用者崗位
     *
     * @param userPostList 使用者崗位列表
     * @return 新增的筆數
     */
    int batchUserPost(List<SysUserPost> userPostList);
}
