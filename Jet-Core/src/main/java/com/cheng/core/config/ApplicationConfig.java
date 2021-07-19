package com.cheng.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

@Configuration
// 表示通過AOP顯示該代理物件讓AopContext可以存取
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.cheng.**.mapper")
public class ApplicationConfig {
    /**
     * 時區設定
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
