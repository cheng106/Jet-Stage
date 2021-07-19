package com.cheng.common.utils.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitManager {

    public static Retrofit getRetrofit(String url) {
        OkHttpClient okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClientBuilder).build();
    }

}
