package com.cheng.web.controller.system;

import com.cheng.common.core.domain.model.LoginBody;
import com.cheng.common.core.domain.response.JsonResponse;
import com.cheng.core.web.service.SysLoginService;
import com.cheng.core.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public JsonResponse login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        Map<String, String> param = new HashMap<>();
        param.put("token", token);
        return JsonResponse.create(param);
    }
}
