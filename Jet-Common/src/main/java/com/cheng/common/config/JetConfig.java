package com.cheng.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jet")
public class JetConfig {
    /**
     * 專案名稱
     **/
    private String name;
    /**
     * 版本
     **/
    private String version;
    /**
     * 版權年份
     **/
    private String copyrightYear;
    /**
     * 上傳路徑
     **/
    private static String profile;
    /**
     * 是否取得地址
     **/
    private static boolean addressEnabled;
    private boolean demoEnabled;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        JetConfig.profile = profile;
    }


    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        JetConfig.addressEnabled = addressEnabled;
    }

    /**
     * 取得圖案上傳路徑
     **/
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /**
     * 取得下載路徑
     **/
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /**
     * 取得上傳路徑
     **/
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }

}
