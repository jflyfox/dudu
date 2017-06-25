package com.jflyfox.dudu.component.interceptor;

import com.jflyfox.util.StrUtils;
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
        String ctxPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

        request.setAttribute("ctxPath", ctxPath);
        String ctx = StrUtils.isEmpty(request.getContextPath()) ? "" : ("/" + request.getContextPath());
        request.setAttribute("ctx", ctx);
        // 标题
        request.setAttribute("headTitle", "FLY的狐狸");
        // 当前时间
        request.setAttribute("now", System.currentTimeMillis());
    }

}
