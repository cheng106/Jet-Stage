package com.cheng.system.mapper;


import com.cheng.system.domain.SysLoginInfo;

import java.util.List;

/**
 * 系統訪問日誌情況資訊
 */
public interface SysLoginInfoMapper {
    /**
     * 新增系統登入日誌
     *
     * @param sysLoginInfo 日誌物件
     */
    void insertLoginInfo(SysLoginInfo sysLoginInfo);

    /**
     * 查詢系統登入日誌列表
     *
     * @param sysLoginInfo 日誌物件
     * @return 登入紀錄列表
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
     *
     * @return 清空的筆數
     */
    int cleanLoginInfo();
}
