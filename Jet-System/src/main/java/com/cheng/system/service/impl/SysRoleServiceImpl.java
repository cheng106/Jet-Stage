package com.cheng.system.service.impl;

import com.cheng.common.core.domain.entity.SysRole;
import com.cheng.system.mapper.SysRoleMapper;
import com.cheng.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public List<SysRole> selectRolePermissionByUserId(Long userId) {
        return roleMapper.selectRolePermissionByUserId(userId);
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }

    @Override
    public SysRole selectRoleById(Long roleId) {
        return roleMapper.selectRoleById(roleId);
    }

    @Override
    public List<SysRole> selectRolesByUserName(String userName) {
        return roleMapper.selectRolesByUserName(userName);
    }

    @Override
    public SysRole checkRoleNameUnique(String roleName) {
        return roleMapper.checkRoleNameUnique(roleName);
    }

    @Override
    public SysRole checkRoleKeyUnique(String roleKey) {
        return roleMapper.checkRoleKeyUnique(roleKey);
    }

    @Override
    public int updateRole(SysRole role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int insertRole(SysRole role) {
        return roleMapper.insertRole(role);
    }

    @Override
    public int deleteRoleById(Long roleId) {
        return roleMapper.deleteRoleById(roleId);
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        return roleMapper.deleteRoleByIds(roleIds);
    }
}
