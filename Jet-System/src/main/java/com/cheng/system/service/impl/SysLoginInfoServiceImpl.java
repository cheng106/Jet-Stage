package com.cheng.system.service.impl;

import com.cheng.system.domain.SysLoginInfo;
import com.cheng.system.mapper.SysLoginInfoMapper;
import com.cheng.system.service.SysLoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLoginInfoServiceImpl implements SysLoginInfoService {

    @Resource
    private SysLoginInfoMapper loginInfoMapper;

    @Override
    public void insertLoginInfo(SysLoginInfo sysLoginInfo) {
        loginInfoMapper.insertLoginInfo(sysLoginInfo);
    }

    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo sysLoginInfo) {
        return loginInfoMapper.selectLoginInfoList(sysLoginInfo);
    }

    @Override
    public int deleteLoginInfoByIds(Long[] infoIds) {
        return loginInfoMapper.deleteLoginInfoByIds(infoIds);
    }

    @Override
    public void cleanLoginInfo() {
        loginInfoMapper.cleanLoginInfo();
    }
}
