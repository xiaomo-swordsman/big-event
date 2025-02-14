package com.xiaomo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    // 向redis中 存入值
    public static void set(String key, String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    // 设置值的时候，同时设置过期时间  TimeUnit.HOURS
    public static void set(String key, String value,long timeOut,TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key,value,timeOut, timeUnit);
    }

    // 从redis中取值
    public static String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    // 从redis中删除值
    public static void del(String key){
        stringRedisTemplate.delete(key);
    }

    /*
        用户修改密码，但是仍然使用原来的token，依然可以访问项目，这里存在bug，需要修改
        1、用户修改密码之后，需要删除掉token
        2、用户登录之后，将最新的token存入到redis，有效期和jwt设置的时候保持一致
        3、在拦截器里面，校验redis中的token，如果token为空，则判定为未登录
     */

}
