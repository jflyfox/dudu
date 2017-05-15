package com.jflyfox.dudu.module.system.model;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/9.
 */
public enum LogOperType {
    LOGIN("登入"), LOGOUT("登出"), INSERT("添加"), UPDATE("更新"), DELETE("删除");

    private String value;

    LogOperType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
