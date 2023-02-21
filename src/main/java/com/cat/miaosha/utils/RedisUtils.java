package com.cat.miaosha.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.xin
 */
@Slf4j
@Component
public class RedisUtils {


    private static RedisTemplate<String, Object> REDIS_TEMPLATE;

    private static StringRedisTemplate STRING_REDIS_TEMPLATE;


    public RedisUtils(@Autowired RedisTemplate<String, Object> redisTemplate, @Autowired StringRedisTemplate stringRedisTemplate) {
        REDIS_TEMPLATE = redisTemplate;
        STRING_REDIS_TEMPLATE = stringRedisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                REDIS_TEMPLATE.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return boolean
     */
    public static boolean expire(String key, long time) {
        try {
            if (time > 0) {
                REDIS_TEMPLATE.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("设置redis指定key失效时间错误:", e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效 失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1）
     */
    public static Long getExpire(String key) {
        return REDIS_TEMPLATE.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false 不存在
     */
    public static Boolean hasKey(String key) {
        try {
            return REDIS_TEMPLATE.hasKey(key);
        } catch (Exception e) {
            log.error("redis判断key是否存在错误：", e);
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                REDIS_TEMPLATE.delete(key[0]);
            } else {
                REDIS_TEMPLATE.delete(Arrays.asList(key));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return key == null ? null : (T) REDIS_TEMPLATE.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            REDIS_TEMPLATE.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("设置redis缓存错误：", e);
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                REDIS_TEMPLATE.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("设置redis缓存错误：", e);
            return false;
        }
    }

    /**
     * 递增 此时value值必须为int类型 否则报错
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return 增加后的值
     */
    public static Long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return STRING_REDIS_TEMPLATE.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return 减少后的值
     */
    public static Long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return REDIS_TEMPLATE.opsForValue().increment(key, -delta);
    }

}
