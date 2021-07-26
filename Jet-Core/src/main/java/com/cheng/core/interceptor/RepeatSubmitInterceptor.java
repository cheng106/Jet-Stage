package com.cheng.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.cheng.common.annotation.RepeatSubmit;
import com.cheng.common.core.domain.response.JsonResponse;
import com.cheng.common.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request)) {
                    JsonResponse jsonResponse = JsonResponse.error(HttpStatus.FORBIDDEN.value(), "不允许重复提交，请稍后再试");
                    ServletUtils.renderString(response, JSONObject.toJSONString(jsonResponse));
                    return false;
                }
            }
            return true;
        } else {
            return preHandle(request, response, handler);
        }
    }

    /**
     * 驗證是否重複提交由子類實現防止重複提交規則
     *
     * @param request request
     * @return boolean 是否重複提交
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}
