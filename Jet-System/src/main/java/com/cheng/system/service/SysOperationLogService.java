package com.cheng.system.service;

import com.cheng.system.domain.SysOperationLog;

import java.util.List;

public interface SysOperationLogService {
    /**
     * 新增日誌
     *
     * @param operationLog 日誌物件
     */
    void insertOperationLog(SysOperationLog operationLog);

    /**
     * 查詢系統操作日誌列表
     *
     * @param operationLog 日誌物件
     * @return 日誌物件列表
     */
    List<SysOperationLog> selectOperationLogList(SysOperationLog operationLog);

    /**
     * 批量删除系统操作日志
     *
     * @param operationIds 需要删除的操作日志ID
     * @return 结果
     */
    int deleteOperationLogByIds(Long[] operationIds);

    /**
     * 查询操作日志详细
     *
     * @param operationId 操作ID
     * @return 操作日志对象
     */
    SysOperationLog selectOperationLogById(Long operationId);

    /**
     * 清空操作日志
     */
    void cleanOperationLog();
}
