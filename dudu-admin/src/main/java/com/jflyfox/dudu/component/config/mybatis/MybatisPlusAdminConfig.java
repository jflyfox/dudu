package com.jflyfox.dudu.component.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.jflyfox.dudu.component.config.properties.DruidAdminProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Administrator on 2017/4/18.
 */
@Configuration
@MapperScan(basePackages = {"com.jflyfox.dudu.module.*.dao", "com.jflyfox.dudu.component.*.dao"})
public class MybatisPlusAdminConfig {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusAdminConfig.class);

    @Autowired
    DruidAdminProperties druidAdminProperties;

    /**
     * druid数据库连接池
     */
    @Bean(name = "adminDataSource")
    public DruidDataSource dataSource() {
        logger.warn("####adminDataSource init start......");
        DruidDataSource dataSource = new DruidDataSource();
        druidAdminProperties.coinfig(dataSource);
        logger.warn("####adminDataSource init finish.");
        return dataSource;
    }

}
