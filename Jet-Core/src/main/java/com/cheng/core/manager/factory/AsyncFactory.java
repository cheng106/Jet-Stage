package com.cheng.core.manager.factory;

import com.cheng.common.constant.Constants;
import com.cheng.common.utils.LogUtils;
import com.cheng.common.utils.ServletUtils;
import com.cheng.common.utils.SpringUtils;
import com.cheng.common.utils.ip.AddressUtils;
import com.cheng.common.utils.ip.IpUtils;
import com.cheng.system.domain.SysLoginInfo;
import com.cheng.system.domain.SysOperationLog;
import com.cheng.system.service.SysLoginInfoService;
import com.cheng.system.service.SysOperationLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 非同步工廠 (產生任務用)
 */
public class AsyncFactory {

    private static final Logger SYS_USER_LOGGER = LoggerFactory.getLogger("sys-user");

    /**
     * 紀錄登入資訊
     *
     * @param username 使用者名稱
     * @param status   狀態
     * @param message  訊息
     * @param args     參數列表
     * @return 任務
     */
    public static TimerTask recordLoginInfo(final String username, final String status,
                                            final String message, final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                // 將訊息印到日誌
                String s = LogUtils.getBlock(ip)
                        + (address)
                        + (LogUtils.getBlock(userAgent))
                        + (LogUtils.getBlock(status))
                        + (LogUtils.getBlock(message));
                SYS_USER_LOGGER.info(s, args);
                // 取得客戶端作業系統
                String os = userAgent.getOperatingSystem().getName();
                // 取得客戶端瀏覽器
                String browser = userAgent.getBrowser().getName();

                // 封裝物件
                SysLoginInfo loginInfo = new SysLoginInfo() {
                    private static final long serialVersionUID = -618286906507241223L;

                    {
                        setUserName(username);
                        setIpaddr(ip);
                        setLoginLocation(address);
                        setBrowser(browser);
                        setOs(os);
                        setMsg(message);
                    }
                };

                // 日誌狀態
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginInfo.setStatus(Constants.LOGIN_SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus(Constants.FAIL);
                }

                // 寫入日誌
                SpringUtils.getBean(SysLoginInfoService.class).insertLoginInfo(loginInfo);
            }
        };
    }

    /**
     * 行為日誌紀錄
     *
     * @param operationLog 日誌物件
     * @return 任務
     */
    public static TimerTask recordOperation(final SysOperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 遠端查詢操作地點
                operationLog.setOperationLocation(AddressUtils.getRealAddressByIP(operationLog.getOperationIp()));
                SpringUtils.getBean(SysOperationLogService.class).insertOperationLog(operationLog);
            }
        };
    }

}
