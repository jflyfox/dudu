package com.jflyfox.dudu.component.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/23.
 */
@Configuration
@Order(100)
public class DuduConfig {

    @Bean
    public Dudu dudu() {
        return new Dudu();
    }
}

class Dudu {

    private static final Logger logger = LoggerFactory.getLogger(DuduConfig.class);

    @PostConstruct
    public void init(){
        logger.warn("####dudu init");
        // 打开fastjson autotype 功能
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public Dudu() {
        super();
        logger.warn("####dudu construct");
    }

    @PreDestroy
    public void destroy(){
        logger.warn("####dudu destroy");
    }
}
