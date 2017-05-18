package com.jflyfox.dudu.component.common;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/20.
 */
public class Constants {

    /**
     * 后台路径
     */
    public static final String[] BACK_PATHS = {"demo", "system", "report", "admin"};

    /**
     * 后台忽略路径
     */
    public static final String[] BACK_ANON_PATHS = {"admin/login", "admin/imagecode", "admin/trans", "admin/logout"};

    public static final String BACK_LOGIN_URL = "/admin/login";

    public static String[] getBackPatternsList() {
        String[] backsPatterns = new String[BACK_PATHS.length];
        for (int i = 0; i < BACK_PATHS.length; i++) {
            backsPatterns[i] = "/" + BACK_PATHS[i] + "/**";
        }
        return  backsPatterns;
    }
}
