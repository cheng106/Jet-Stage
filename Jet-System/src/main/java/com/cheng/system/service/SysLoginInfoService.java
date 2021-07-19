package com.cheng.system.service;

import com.cheng.system.domain.SysLoginInfo;

import java.util.List;

public interface SysLoginInfoService {
    /**
     * 新增系統登入日誌
     *
     * @param sysLoginInfo 日誌物件
     */
    void insertLoginInfo(SysLoginInfo sysLoginInfo);

    /**
     * 查询系统登录日志集合
     *
     * @param sysLoginInfo 日誌物件
     * @return 登入紀錄集合
     */
    List<SysLoginInfo> selectLoginInfoList(SysLoginInfo sysLoginInfo);

    /**
     * 批次刪除系統登入日誌
     *
     * @param infoIds 要刪除的登入日誌ID
     * @return 刪除的筆數
     */
    int deleteLoginInfoByIds(Long[] infoIds);

    /**
     * 清空系統登入日誌
     */
    void cleanLoginInfo();
}
