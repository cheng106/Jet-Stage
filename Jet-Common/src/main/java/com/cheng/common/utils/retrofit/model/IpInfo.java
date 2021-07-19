package com.cheng.common.utils.retrofit.model;

import lombok.Data;

@Data
public class IpInfo {

    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private Long lat;
    private Long lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
    private String query;

}
