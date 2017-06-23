package com.jflyfox.dudu.component.config.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by flyfox 369191470@qq.com on 2017/6/24.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 取得当前使用哪个数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
