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
public class MybatisMultiConfig {
    private static final Logger logger = LoggerFactory.getLogger(MybatisMultiConfig.class);

    /**
     * druid数据库连接池
     */
    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource(@Qualifier("defaultDataSource") DruidDataSource defaultDataSource
            , @Qualifier("adminDataSource") DruidDataSource adminDataSource
            , @Qualifier("appDataSource") DruidDataSource appDataSource) {
        logger.warn("####dataSource init start......");
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put(DBTypeEnum.dataSource.getValue(), defaultDataSource);
        map.put(DBTypeEnum.admin.getValue(), adminDataSource);
        map.put(DBTypeEnum.app.getValue(), appDataSource);
        dataSource.setTargetDataSources(map);
        dataSource.setDefaultTargetDataSource(defaultDataSource);
        logger.warn("####dataSource init finish.");
        return dataSource;
    }

}
