package com.jflyfox.dudu.component.common;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/20.
 */
public class Constants {

    /**
     * 用户Session
     */
    public static final String SESSION_USER = "sessionUser";

    /**
     * 后台路径
     */
    public static final String[] backPaths = {"demo", "system", "report", "admin"};

    public static String[] getBackPatternsList() {
        String[] backsPatterns = new String[backPaths.length];
        for (int i = 0; i < backPaths.length; i++) {
            backsPatterns[i] = "/" + backPaths[i] + "/**";
        }
        return  backsPatterns;
    }
}
