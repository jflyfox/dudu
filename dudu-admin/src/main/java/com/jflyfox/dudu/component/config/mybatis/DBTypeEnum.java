package com.jflyfox.dudu.component.config.mybatis;

/**
 * Created by flyfox 369191470@qq.com on 2017/6/24.
 */
public enum DBTypeEnum {
    dataSource("defaultDataSource"), app("appDataSource"), admin("adminDataSource");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
