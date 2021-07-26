package com.cheng.common.core.domain.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.springframework.http.HttpStatus;

public class JsonResponse extends JSONObject implements BaseResponse {

    private static final long serialVersionUID = 1000300767098532180L;
    private static final SerializeConfig SERIALIZE_CONFIG;

    static {
        SERIALIZE_CONFIG = new SerializeConfig();
        SERIALIZE_CONFIG.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
    }

    private JsonResponse(JSONObject json) {
        super(json);
    }

    public static <T> JsonResponse create(T obj) {
        JSONObject json = (JSONObject) JSON.toJSON(obj, SERIALIZE_CONFIG);
        return new JsonResponse(json);
    }

    public static String success(String msg) {
        JSONObject j = new JSONObject();
        j.put("code", HttpStatus.OK);
        j.put("msg", msg);
        return JsonResponse.create(j).toJSONString();
    }

    public static JsonResponse error(int code, String msg) {
        JSONObject j = new JSONObject();
        j.put("code", code);
        j.put("msg", msg);
        return new JsonResponse(j);
    }

}
