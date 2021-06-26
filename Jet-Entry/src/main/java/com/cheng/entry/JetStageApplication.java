package com.cheng.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JetStageApplication {
    public static void main(String[] args) {
        SpringApplication.run(JetStageApplication.class, args);
        System.out.println("Service Start Success─=≡Σ((( つ•̀ω•́)つ");
    }
}
