package com.cheng.common.utils.ip;

import com.alibaba.fastjson.JSONObject;
import com.cheng.common.config.JetConfig;
import com.cheng.common.constant.Constants;
import com.cheng.common.utils.StringUtils;
import com.cheng.common.utils.http.HttpUtils;
import com.cheng.common.utils.retrofit.IpApiService;
import com.cheng.common.utils.retrofit.RetrofitManager;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * 取得大概位置
 */
@Slf4j
public class AddressUtils {

    // IP查詢位置
//    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
    private static final String GET_IP_INFO_URL = "http://ip-api.com/json/";
    // 未知位置
    public static final String UNKNOWN = "UNKNOWN";

    public static String getRealAddressByIP(String ip) {
        // 內網不查詢
        if (IpUtils.internalIp(ip)) {
            return "內網IP";
        }
        if (JetConfig.isAddressEnabled()) {
            try {
                HashMap<String, String> map = new HashMap<String, String>() {{
                    put("ip", ip);
                }};
                String rspStr = HttpUtils.sendGet(GET_IP_INFO_URL, map, Constants.UTF8);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("無法取得位置:{}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String isp = obj.getString("isp");
                String city = obj.getString("city");
                return String.format("%s %s", isp, city);
            } catch (Exception e) {
                log.error("###無法取得位置: {}", ip);
            }
        }
        return UNKNOWN;
    }

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = RetrofitManager.getRetrofit(GET_IP_INFO_URL);
        IpApiService ipApiService = retrofit.create(IpApiService.class);
        Call<JSONObject> call = ipApiService.getIpInfo("220.135.16.58");
        Response<JSONObject> response = call.execute();
        Optional<JSONObject> info = Optional.ofNullable(response.body());
        info.ifPresent(jsonObject -> System.out.println("info isp = " + jsonObject.getString("isp")));

//
//        call.enqueue(new Callback<IpInfo>() {
//            @Override
//            public void onResponse(Call<IpInfo> call, Response<IpInfo> response) {
//                String org = Objects.requireNonNull(response.body()).getOrg();
//                String city = Objects.requireNonNull(response.body()).getCity();
//
//                System.out.println("org = " + org);
//                System.out.println("city = " + city);
//            }
//
//            @Override
//            public void onFailure(Call<IpInfo> call, Throwable throwable) {
//                System.out.println("Fail:" + throwable.getMessage());
//            }
//        });

//        String result = getRealAddressByIP("220.135.16.58");
//        System.out.println("result = " + result);
    }
}
