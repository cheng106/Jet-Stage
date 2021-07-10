package com.cheng.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidProperties {

    @Value("${spring.datasource.druid.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.druid.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.druid.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.druid.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private int maxEvictableIdleTimeMillis;

    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.druid.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.druid.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.druid.testOnReturn}")
    private boolean testOnReturn;

    public DruidDataSource dataSource(DruidDataSource dataSource) {
        // 設定初始化大小、最小、最大
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);

        // 設定取得連接等待逾時的時間
        dataSource.setMaxWait(maxWait);

        // 設定間格多久進行檢測，檢測需要關閉空閒連接，單位毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);

        // 設定一個連接在池中最小、最大生存的時間，單位毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);

        // 用來檢測連接是否有效的SQL，要求是一個查詢語句，常用select 'x'。如果validationQuery為null，testOnBorrow、testOnReturn、testWhileIdle都沒用。
        dataSource.setValidationQuery(validationQuery);
        // 建議設定為true，不影響效能並保證安全性。申請連接的時候檢測，如果空閒時間大於timeBetweenEvictionRunsMillis，執行validationQuery檢測連接是否有效。
        dataSource.setTestWhileIdle(testWhileIdle);
        // 申請連接時執行validationQuery檢測連接是否有效，使用這設定效能會降低。
        dataSource.setTestOnBorrow(testOnBorrow);
        // 歸還連接時執行validationQuery檢測連接是否有效，使用這設定效能會降低。
        dataSource.setTestOnReturn(testOnReturn);
        return dataSource;
    }
}
