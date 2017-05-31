package com.jflyfox.dudu.component.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/18.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        KeyGenerator keyGenerator = (Object target, Method method, Object... params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            if (params != null && params.length > 0) {
                sb.append(":");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
        return keyGenerator;
    }

    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        rcm.setDefaultExpiration(30 * 24 * 3600L);//秒
        //设置value的过期时间
        Map<String, Long> map = new HashMap();
        map.put("test", 60L); // test key 为60秒
        rcm.setExpires(map);
        return rcm;
    }

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<>(Object.class);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(fastJson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
