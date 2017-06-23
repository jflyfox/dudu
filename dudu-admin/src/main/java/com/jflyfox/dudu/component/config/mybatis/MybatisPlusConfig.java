package com.jflyfox.dudu.component.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.jflyfox.dudu.component.config.properties.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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
    @Bean(name = "defaultDataSource")
    public DruidDataSource dataSource() {
        logger.warn("####defaultDataSource init start......");
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.coinfig(dataSource);
        logger.warn("####defaultDataSource init finish.");
        return dataSource;
    }

}
