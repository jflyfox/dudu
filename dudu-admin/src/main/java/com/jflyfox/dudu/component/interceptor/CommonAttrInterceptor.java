package com.jflyfox.dudu.component.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/4/18.
 */
public class CommonAttrInterceptor extends HandlerInterceptorAdapter {

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        request.setAttribute("BASE_PATH", basePath);
        request.setAttribute("ctx", basePath);

        String currentPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        currentPath += request.getRequestURI(); // 参数
        String currentPathParam = currentPath;
        if (request.getQueryString() != null) // 判断请求参数是否为空
            currentPathParam += "?" + request.getQueryString(); // 参数
        // 无参数
        request.setAttribute("CURRENT_PATH", currentPath);
        // 有参数
        request.setAttribute("CURRENT_PATH_PARAM", currentPathParam);

        // 有参数
        request.setAttribute("HEAD_TITLE", "FLY的狐狸");
    }

}
