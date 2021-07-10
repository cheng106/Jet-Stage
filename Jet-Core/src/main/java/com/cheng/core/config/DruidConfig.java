package com.cheng.core.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import com.cheng.common.enums.DataSourceType;
import com.cheng.common.utils.SpringUtils;
import com.cheng.core.datasource.DynamicDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.*;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties properties) {
        return properties.dataSource(DruidDataSourceBuilder.create().build());
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties properties) {
        return properties.dataSource(DruidDataSourceBuilder.create().build());
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
//        setDataSource(targetDataSources, DataSourceType.SLAVE.name(), "slaveDataSource");
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }

    /**
     * 設定資料連接來源
     *
     * @param targetDataSources 備援DataSource的容器
     * @param sourceName        資料連接來源名稱
     * @param beanName          bean的名稱
     */
    private void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
        DataSource dataSource = SpringUtils.getBean(beanName, DataSource.class);
        targetDataSources.put(sourceName, dataSource);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean<Filter> removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 取得web監控頁面的參數
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();

        // 取得common.js的設定路徑
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        String filePath = "support/http/resource/js/common.js";

        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter((servletRequest, servletResponse, filterChain) -> {
                    filterChain.doFilter(servletRequest, servletResponse);
                    // 將緩衝區重置，回應標頭不會影響
                    servletResponse.resetBuffer();
                    // 取得common.js
                    String text = Utils.readFromResource(filePath);
                    // 正則表達式替換banner，將底部的廣告消除
                    text = text.replaceAll("<a.*?banner\"></a><br/>", "");
                    text = text.replaceAll("powered.*?shrek.wang</a>", "");
                    servletResponse.getWriter().write(text);
                }
        );
        registration.addUrlPatterns(commonJsPattern);
        return registration;
    }

//    @Bean
//    public FilterRegistrationBean<Filter> removeDruidAdFilter() throws IOException {
//        // 取得common.js內容
//        String text = Utils.readFromResource("support/http/resources/js/common.js");
//        // 替換有廣告的那一段程式碼
//        String newJs = text.replace("this.buildFooter();", "");
//        // 使用過濾器註冊物件
//        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
//        // 註冊common.js的過濾器
//        registration.addUrlPatterns("/druid/js/common.js");
//        // 新增一個過濾器，並把改過的common.js內容寫到瀏覽器
//        registration.setFilter((servletRequest, servletResponse, filterChain) -> {
//            // 重置缓冲区，响应头不会被重置
//            servletResponse.resetBuffer();
//            // 把改過的common.js內容寫到瀏覽器
//            servletResponse.getWriter().write(newJs);
//        });
//        return registration;
//    }

}
