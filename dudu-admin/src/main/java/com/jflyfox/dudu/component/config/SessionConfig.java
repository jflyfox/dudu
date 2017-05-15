package com.jflyfox.dudu.component.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * spring session redis
 * Created by flyfox 369191470@qq.com on 2017/5/13.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30) // 默认30天
public class SessionConfig {
}
