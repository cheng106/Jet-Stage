package com.cheng.common.utils.retrofit;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IpApiService {

    @GET("{ip}")
    Call<JSONObject> getIpInfo(@Path("ip") String ip);
}
