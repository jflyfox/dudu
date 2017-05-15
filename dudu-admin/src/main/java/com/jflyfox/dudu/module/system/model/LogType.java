package com.jflyfox.dudu.module.system.model;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/9.
 */
public enum LogType {
    SYSTEM(1), OPERATE(2);

    private int value;

    LogType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
