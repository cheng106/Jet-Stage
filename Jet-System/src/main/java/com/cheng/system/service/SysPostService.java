package com.cheng.system.service;

import com.cheng.system.domain.SysPost;

import java.util.List;

public interface SysPostService {
    /**
     * 查詢崗位資料
     *
     * @param post 崗位訊息
     * @return 崗位資料列表
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     * 查詢所有崗位
     *
     * @return 崗位列表
     */
    List<SysPost> selectPostAll();

    /**
     * 根據崗位ID查詢崗位訊息
     *
     * @param postId 崗位ID
     * @return 崗位物件
     */
    SysPost selectPostById(Long postId);

    /**
     * 根據使用者ID取得崗位選擇框列表
     *
     * @param userId 使用者ID
     * @return 選中崗位ID列表
     */
    List<Integer> selectPostListByUserId(Long userId);

    /**
     * 查詢使用者所属崗位群組
     *
     * @param userName 使用者名稱
     * @return 结果
     */
    List<SysPost> selectPostsByUserName(String userName);

    /**
     * 刪除崗位訊息
     *
     * @param postId 崗位ID
     * @return 结果
     */
    int deletePostById(Long postId);

    /**
     * 批次刪除崗位訊息
     *
     * @param postIds 要刪除的崗位ID
     * @return 结果
     */
    int deletePostByIds(Long[] postIds);

    /**
     * 修改崗位訊息
     *
     * @param post 崗位訊息
     * @return 结果
     */
    int updatePost(SysPost post);

    /**
     * 新增崗位訊息
     *
     * @param post 崗位訊息
     * @return 结果
     */
    int insertPost(SysPost post);

    /**
     * 檢查崗位名稱
     *
     * @param postName 崗位名稱
     * @return 结果
     */
    SysPost checkPostNameUnique(String postName);

    /**
     * 檢查崗位編號
     *
     * @param postCode 崗位編號
     * @return 结果
     */
    SysPost checkPostCodeUnique(String postCode);
}
