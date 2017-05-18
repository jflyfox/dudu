package com.jflyfox.dudu.component.util;

import com.jflyfox.dudu.component.common.Constants;
import com.jflyfox.util.StrUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * web工具类
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/5/18.
 */
public class DuduWebUtils {

    /**
     * 后台路径
     *
     * @param request
     * @return
     */
    public static boolean isBack(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        requestPath = StrUtils.isEmpty(request.getContextPath()) ? requestPath : requestPath.substring(request.getContextPath().length());
        if (requestPath.startsWith("/")) {
            requestPath = requestPath.substring(1, requestPath.length());
        }
        if (requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        for (int i = 0; i < Constants.BACK_PATHS.length; i++) {
            if (requestPath.startsWith(Constants.BACK_PATHS[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 后台不认证路径
     *
     * @param request
     * @return
     */
    public static boolean isBackAnon(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        requestPath = StrUtils.isEmpty(request.getContextPath()) ? requestPath : requestPath.substring(request.getContextPath().length());
        if (requestPath.startsWith("/")) {
            requestPath = requestPath.substring(1, requestPath.length());
        }
        if (requestPath.endsWith("/")) {
            requestPath = requestPath.substring(0, requestPath.length() - 1);
        }

        for (int i = 0; i < Constants.BACK_ANON_PATHS.length; i++) {
            if (requestPath.startsWith(Constants.BACK_ANON_PATHS[i])) {
                return true;
            }
        }
        return false;
    }
}
