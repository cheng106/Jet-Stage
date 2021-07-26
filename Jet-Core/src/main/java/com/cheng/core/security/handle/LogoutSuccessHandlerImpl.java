package com.cheng.core.security.handle;

import com.cheng.common.constant.Constants;
import com.cheng.common.core.domain.model.LoginUser;
import com.cheng.common.core.domain.response.JsonResponse;
import com.cheng.common.utils.ServletUtils;
import com.cheng.common.utils.StringUtils;
import com.cheng.core.manager.AsyncManager;
import com.cheng.core.manager.factory.AsyncFactory;
import com.cheng.core.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUser user = tokenService.getLoginUser(httpServletRequest);
        if (StringUtils.isNotNull(user)) {
            String userName = user.getUsername();
            // 刪除使用者的暫存紀錄
            tokenService.delLoginUser(user.getToken());
            // 紀錄日誌
            AsyncManager.me().execute(AsyncFactory.recordLoginInfo(userName, Constants.LOGOUT, "登出成功"));
        }

        ServletUtils.renderString(httpServletResponse, JsonResponse.success("登出成功"));
    }
}
