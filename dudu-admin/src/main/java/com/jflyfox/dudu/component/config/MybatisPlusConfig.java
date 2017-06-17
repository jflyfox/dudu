package com.jflyfox.dudu.component.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.github.pagehelper.PageInterceptor;
import com.jflyfox.dudu.component.config.properties.DruidProperties;
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
public class MybatisPlusConfig {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

    @Autowired
    DruidProperties druidProperties;

//    /**
//     * mybatis-plus分页插件
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
//        return paginationInterceptor;
//    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        logger.warn("####dataSource init start......");
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.coinfig(dataSource);
        logger.warn("####dataSource init finish.");
        return dataSource;
    }

}
