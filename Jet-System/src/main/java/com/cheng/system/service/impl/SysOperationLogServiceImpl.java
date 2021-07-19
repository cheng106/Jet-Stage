package com.cheng.system.service.impl;

import com.cheng.system.domain.SysOperationLog;
import com.cheng.system.mapper.SysOperationLogMapper;
import com.cheng.system.service.SysOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {
    
    @Resource
    private SysOperationLogMapper operationLogMapper;

    @Override
    public void insertOperationLog(SysOperationLog operationLog) {
        operationLogMapper.insertOperationLog(operationLog);
    }

    @Override
    public List<SysOperationLog> selectOperationLogList(SysOperationLog operationLog) {
        return operationLogMapper.selectOperationLogList(operationLog);
    }

    @Override
    public int deleteOperationLogByIds(Long[] operationIds) {
        return operationLogMapper.deleteOperationLogByIds(operationIds);
    }

    @Override
    public SysOperationLog selectOperationLogById(Long operationId) {
        return operationLogMapper.selectOperationLogById(operationId);
    }

    @Override
    public void cleanOperationLog() {
        operationLogMapper.cleanOperationLog();
    }
}
