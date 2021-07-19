package com.cheng.system.service.impl;

import com.cheng.system.domain.SysPost;
import com.cheng.system.mapper.SysPostMapper;
import com.cheng.system.service.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysPostServiceImpl implements SysPostService {

    @Resource
    private SysPostMapper postMapper;

    @Override
    public List<SysPost> selectPostList(SysPost post) {
        return postMapper.selectPostList(post);
    }

    @Override
    public List<SysPost> selectPostAll() {
        return postMapper.selectPostAll();
    }

    @Override
    public SysPost selectPostById(Long postId) {
        return postMapper.selectPostById(postId);
    }

    @Override
    public List<Integer> selectPostListByUserId(Long userId) {
        return postMapper.selectPostListByUserId(userId);
    }

    @Override
    public List<SysPost> selectPostsByUserName(String userName) {
        return postMapper.selectPostsByUserName(userName);
    }

    @Override
    public int deletePostById(Long postId) {
        return postMapper.deletePostById(postId);
    }

    @Override
    public int deletePostByIds(Long[] postIds) {
        return postMapper.deletePostByIds(postIds);
    }

    @Override
    public int updatePost(SysPost post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int insertPost(SysPost post) {
        return postMapper.insertPost(post);
    }

    @Override
    public SysPost checkPostNameUnique(String postName) {
        return postMapper.checkPostNameUnique(postName);
    }

    @Override
    public SysPost checkPostCodeUnique(String postCode) {
        return postMapper.checkPostCodeUnique(postCode);
    }
}
