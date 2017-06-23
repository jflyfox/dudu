package com.jflyfox.dudu.component.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.jflyfox.dudu.component.config.properties.DruidAppProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/4/18.
 */
@Configuration
@MapperScan(basePackages = {"com.jflyfox.dudu.module.*.dao", "com.jflyfox.dudu.component.*.dao"})
public class MybatisPlusAppConfig {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusAppConfig.class);

    @Autowired
    DruidAppProperties druidAppProperties;

    /**
     * druid数据库连接池
     */
    @Bean(name = "appDataSource")
    public DruidDataSource dataSource() {
        logger.warn("####appDataSource init start......");
        DruidDataSource dataSource = new DruidDataSource();
        druidAppProperties.coinfig(dataSource);
        logger.warn("####appDataSource init finish.");
        return dataSource;
    }

}
