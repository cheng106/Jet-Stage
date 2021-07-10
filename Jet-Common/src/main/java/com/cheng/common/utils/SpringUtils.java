package com.cheng.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SpringUtils implements ApplicationContextAware, BeanFactoryPostProcessor {

    // Spring Context
    private static ConfigurableListableBeanFactory beanFactory;
    private static ApplicationContext applicationContext;

    // 取得applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = Optional.ofNullable(applicationContext).orElse(context);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory = Optional.ofNullable(beanFactory).orElse(configurableListableBeanFactory);
    }

    // 從ApplicationContext中取得Bean(根據類別載入) (這個applicationContext在啟動時就載入了)
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 從ApplicationContext中取得Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    // 從ApplicationContext中取得Environment，然後再取得配置的文件中的資訊(指定key的值)
    public static String getProperty(String key) {
        return applicationContext.getBean(Environment.class).getProperty(key);
    }

    // 此方法與上面的區別在於可能取得的prop可能不是一個值，可能還有多個子節點
    public static List<String> getConfigValueList(String key) {
        Environment environment = getBean(Environment.class);
        String prop = environment.getProperty(key);
        List<String> items = new ArrayList<>();
        if (StringUtils.isBlank(prop)) {
            return items;
        }
        for (String item : prop.split(",")) {
            item = item.trim();
            if (StringUtils.isNotBlank(item)) {
                items.add(item);
            }
        }
        return items;
    }


}
