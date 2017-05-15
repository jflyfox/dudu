package com.jflyfox.dudu.component.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/4/18.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getServletPath();
        // 不需要认证
        if (!isAuth(path)) {
            return true;
        }

//        if (webUtils.currentUser(request, response) == null) {
//            //未登陆用户，记录访问地址，登陆后可以直接跳转到此页面
//            if (!requestURI.contains("/bbs/user/login.html")) {
//                request.getSession(true).setAttribute("lastAccess", requestURI);
//            }
//        }
//        if (requestURI.contains("/bbs/admin/") || requestURI.contains("/bbs/topic/add")) {
//            BbsUser user = webUtils.currentUser(request, response);
//            if (user == null) {
//                response.sendRedirect(request.getContextPath() + "/user/loginPage.html");
//                return false;
//            }
//        }
        return true;
    }

    public boolean isAuth(String path) {
        // 后台不需要认证页面
        if (path.equals("/")
                || path.startsWith("/admin/login") //
                || path.startsWith("/admin/logout") //
                || path.startsWith("/admin/imagecode") //
                || path.startsWith("/admin/trans")) {
            return false;
        }

        return true;
    }

}
