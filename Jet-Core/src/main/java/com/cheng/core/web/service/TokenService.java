package com.cheng.core.web.service;

import com.cheng.common.constant.Constants;
import com.cheng.common.core.domain.model.LoginUser;
import com.cheng.common.core.redis.RedisService;
import com.cheng.common.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    /**
     * 取得帳號身份訊息
     *
     * @param authToken 從Request Header取得Token
     */
    public LoginUser getLoginUser(@RequestHeader("Authorization") String authToken) {
        String token = getToken(authToken);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            String uuid = String.valueOf(claims.get(Constants.LOGIN_USER_KEY));
            String userKey = getTokenKey(uuid);
            return (LoginUser) redisService.getCacheObject(userKey);
        }
        return null;
    }

    public String createToken(LoginUser user) {
        //TODO: 建立Token...
        return null;
    }

    /**
     * 設定登入帳號身份訊息
     *
     * @param user Login user
     */
    public void setLoginUser(LoginUser user) {
        if (StringUtils.isNotNull(user) && StringUtils.isNotEmpty(user.getToken())) {
            refreshToken(user);
        }
    }

    /**
     * 更新Token有效期限
     *
     * @param user Login user
     */
    public void refreshToken(LoginUser user) {
        user.setLoginTime(System.currentTimeMillis());
        user.setExpireTime(user.getLoginTime() + expireTime * MINUTE_MILLIS);
        // 依據UUID將登入帳號Cache
        String userKey = getTokenKey(user.getToken());
        redisService.setCacheObject(userKey, user, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 解析JWT<br>
     * 簽章的key為自定義
     *
     * @param token Token
     * @return Claims Claims
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 從Request Header取得Token
     *
     * @param token Token
     */
    private String getToken(String token) {
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY.concat(uuid);
    }
}
