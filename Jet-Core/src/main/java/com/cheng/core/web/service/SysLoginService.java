package com.cheng.core.web.service;

import com.cheng.common.constant.Constants;
import com.cheng.common.core.domain.entity.SysUser;
import com.cheng.common.core.domain.model.LoginUser;
import com.cheng.common.core.redis.RedisService;
import com.cheng.common.exception.user.CaptchaException;
import com.cheng.common.exception.user.CaptchaExpireException;
import com.cheng.common.exception.user.UserPasswordNotMatchException;
import com.cheng.common.utils.DateUtils;
import com.cheng.common.utils.MessageUtils;
import com.cheng.common.utils.ServletUtils;
import com.cheng.common.utils.ip.IpUtils;
import com.cheng.core.manager.AsyncManager;
import com.cheng.core.manager.factory.AsyncFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Component
public class SysLoginService {

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

//    @Autowired
//    private SysUserService userService;

    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = String.valueOf(redisService.getCacheObject(verifyKey));
        redisService.deleteObject(verifyKey);

        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.captcha.expire")));
            throw new CaptchaExpireException();
        }

        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.captcha.error")));
            throw new CaptchaException();
        }

        // 使用者驗證
        Authentication authentication = null;

        try {
            // 該方法會去呼叫UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_FAIL, e.getMessage()));
            }
        }

        AsyncManager.me().execute(AsyncFactory.recordLoginInfo(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser user = (LoginUser) Objects.requireNonNull(authentication).getPrincipal();
        recordLoginInfo(user.getUser());
        // gen token
        return tokenService.createToken(user);
    }

    public void recordLoginInfo(SysUser user) {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
//        userService.updateUserProfile(user);
    }
}
