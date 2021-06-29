package com.cheng.core.web.service;

import com.cheng.common.core.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenService {

    /**
     * Token表頭
     **/
    @Value("${token.header}")
    private String header;

    /**
     * Token密鑰
     **/
    @Value("${token.secret}")
    private String secret;

    /**
     * Token有效期限
     **/
    @Value("${token.expireTime}")
    private int expireTime;

    @Resource
    private RedisService redisService;

    private static final long MINUTE_MILLIS = 60 * 1000;
    private static final long TWENTY_MINUTES_MILLIS = 20 * MINUTE_MILLIS;

    public

}
