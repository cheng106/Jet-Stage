package com.cheng.common.utils.ip;

import com.alibaba.fastjson.JSONObject;
import com.cheng.common.config.JetConfig;
import com.cheng.common.constant.Constants;
import com.cheng.common.utils.StringUtils;
import com.cheng.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 取得大概位置
 */
@Slf4j
public class AddressUtils {

    // IP查詢位置
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    // 未知位置
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 內網不查詢
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (JetConfig.isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.UTF8);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("無法取得位置:{}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("###無法取得位置: {}", ip);
            }
        }
        return UNKNOWN;
    }
}
