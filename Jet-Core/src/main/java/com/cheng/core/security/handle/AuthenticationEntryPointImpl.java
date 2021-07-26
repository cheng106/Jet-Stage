package com.cheng.core.security.handle;

import com.alibaba.fastjson.JSON;
import com.cheng.common.core.domain.response.JsonResponse;
import com.cheng.common.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -3673783460623445028L;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        String msg = String.format("請求訪問:%s，認證失敗，無法訪問系統資源", httpServletRequest.getRequestURI());
        ServletUtils.renderString(httpServletResponse,
                JSON.toJSONString(JsonResponse.error(HttpStatus.UNAUTHORIZED.value(), msg)));
    }
}
