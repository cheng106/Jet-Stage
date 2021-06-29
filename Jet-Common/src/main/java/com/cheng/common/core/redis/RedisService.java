package com.cheng.common.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存物件
     *
     * @param key   Cache Key
     * @param value Cache Value
     */
    public void setCacheObject(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存物件
     *
     * @param key      Cache Key
     * @param value    Cache Value
     * @param timeout  逾時時間
     * @param timeUnit 時間單位
     */
    public void setCacheObject(final String key, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 存List
     *
     * @param key      Cache Key
     * @param dataList 要存的List
     * @return 暫存的數量
     */
    public <T> Long setCacheList(String key, List<T> dataList) {
        return redisTemplate.opsForList().rightPushAll(key, dataList);
    }

    /**
     * 取物件
     *
     * @param key Cache Key
     * @return 對應Key的物件
     */
    public Object getCacheObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 取List
     *
     * @param key Cache Key
     * @return 對應Key的物件
     */
    public List<Object> getCacheList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 取集合物件
     *
     * @param pattern 前綴名稱
     * @return 集合物件
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 設定Cache有效時間
     *
     * @param key     Cache Key
     * @param timeout 逾時時間
     * @return true=設定成功，false=設定失敗
     */
    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 設定Cache有效時間
     *
     * @param key     Cache Key
     * @param timeout 逾時時間
     * @param unit    時間單位
     * @return true=設定成功，false=設定失敗
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 刪除一個物件
     *
     * @param key Cache Key
     */
    public Boolean deleteObject(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 刪除集合物件
     *
     * @param collection 集合物件
     * @return 刪除數量
     */
    public Long deleteObject(Collection<String> collection) {
        return redisTemplate.delete(collection);
    }


}
