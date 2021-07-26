package com.cheng.core.interceptor.impl;

import com.alibaba.fastjson.JSONObject;
import com.cheng.common.constant.Constants;
import com.cheng.common.core.redis.RedisService;
import com.cheng.common.filter.RepeatedlyRequestWrapper;
import com.cheng.common.utils.HttpHelper;
import com.cheng.common.utils.StringUtils;
import com.cheng.core.interceptor.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 判斷請求URL和資料是否和上次相同
 * 如果和上次相同，表示重複提交表單(有效時間10秒內)
 */
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {
    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    @Value("${token.header}")
    private String header;

    @Autowired
    private RedisService redisService;

    /**
     * 間隔時間，單位:秒 預設10秒
     * <p>
     * 兩次相同參數的請求，如果間隔時間大於該參數，系統不會認定為重複提交的資料
     */
    private int intervalTime = 10;

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    //    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request) {
        String nowParams = "";
        if (request instanceof RepeatedlyRequestWrapper) {
            RepeatedlyRequestWrapper repeatedlyRequest = (RepeatedlyRequestWrapper) request;
            nowParams = HttpHelper.getBodyString(repeatedlyRequest);
        }

        // body參數為空，取得Parameter的資料
        if (StringUtils.isEmpty(nowParams)) {
            nowParams = JSONObject.toJSONString(request.getParameterMap());
        }
        Map<String, Object> nowDataMap = new HashMap<String, Object>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 請求URL (作為存放cache的key值)
        String url = request.getRequestURI();

        // 唯一值 (沒有消息表頭則使用請求URL)
        String submitKey = request.getHeader(header);
        if (StringUtils.isEmpty(submitKey)) {
            submitKey = url;
        }

        // 唯一值 (指定key + 訊息表頭)
        String cacheRepeatKey = Constants.REPEAT_SUBMIT_KEY + submitKey;

        Object sessionObj = redisService.getCacheObject(cacheRepeatKey);
        if (sessionObj != null) {
            Map<String, Object> sessionMap = (Map<String, Object>) sessionObj;
            if (sessionMap.containsKey(url)) {
                Map<String, Object> preDataMap = (Map<String, Object>) sessionMap.get(url);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap)) {
                    return true;
                }
            }
        }
        Map<String, Object> cacheMap = new HashMap<>();
        cacheMap.put(url, nowDataMap);
        redisService.setCacheObject(cacheRepeatKey, cacheMap, intervalTime, TimeUnit.SECONDS);
        return false;
    }

    /**
     * 判斷參數是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判斷兩次間隔時間
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap) {
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        if ((time1 - time2) < (intervalTime * 1000L)) {
            return true;
        }
        return false;
    }
}
