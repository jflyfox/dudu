package com.jflyfox.dudu.component.interceptor;

import com.jflyfox.dudu.component.model.SQLInject;
import com.jflyfox.dudu.component.util.DuduRespUtils;
import com.jflyfox.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */
public class SQLInjectInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SQLInjectInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        SQLInject annotation = method.getAnnotation(SQLInject.class);
        if (annotation != null && !annotation.value()) {
            return true;
        }

        String pathInfo = request.getPathInfo() == null ? "" : request.getPathInfo();
        String url = request.getServletPath() + pathInfo;

        // 获取请求所有参数，校验防止SQL注入，防止XSS漏洞
        Enumeration<?> params = request.getParameterNames();
        String paramN = null;
        while (params.hasMoreElements()) {
            paramN = (String) params.nextElement();
            String paramVale = request.getParameter(paramN);

            // 校验是否存在SQL注入信息
            if (checkSQLInject(paramVale, url)) {
                // 返回json提示
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(DuduRespUtils.fail(null, "传输参数异常").toJSONString());
                return false;
            }
        }

        return true;
    }

    /**
     * 检查是否存在非法字符，防止SQL注入
     *
     * @param str 被检查的字符串
     * @return ture-字符串中存在非法字符，false-不存在非法字符
     */
    public static boolean checkSQLInject(String str, String url) {
        if (StrUtils.isEmpty(str)) {
            return false;// 如果传入空串则认为不存在非法字符
        }

        // 判断黑名单
        String[] inj_stra = {"script", "mid", "master", "truncate", "insert", "select", "delete", "update", "declare",
                "iframe", "'", "onreadystatechange", "alert", "atestu", "xss", ";", "'", "\"", "<", ">", "(", ")", // ",",
                "\\", "svg", "confirm", "prompt", "onload", "onmouseover", "onfocus", "onerror"};

        str = str.toLowerCase(); // sql不区分大小写

        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) >= 0) {
                logger.error("SQLInject防攻击拦截url:" + url + "，原因：特殊字符，传入str=" + str + ",包含特殊字符：" + inj_stra[i]);
                return true;
            }
        }
        return false;
    }
}
