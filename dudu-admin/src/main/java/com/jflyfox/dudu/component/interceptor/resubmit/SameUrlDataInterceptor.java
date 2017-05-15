package com.jflyfox.dudu.component.interceptor.resubmit;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */

import com.alibaba.fastjson.JSON;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.component.util.DuduRespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * form重复提交，url参数拦截
 * <p>
 * 一个用户 相同url 同时提交 相同数据 验证
 * 主要通过 session中保存到的url 和 请求参数。如果和上次相同，则是重复提交表单
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */
public class SameUrlDataInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SameUrlDataInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null) {
                if (repeatDataValidator(request)) { // 如果重复相同数据
                    // 返回json提示
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(DuduRespUtils.fail(null, "与上次提交数据相同").toJSONString());
                    return false;
                } else {
                    return true;
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证同一个url数据是否相同提交  ,相同返回true
     *
     * @param httpServletRequest
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest) {
        String params = JSON.toJSONString(httpServletRequest.getParameterMap());
        String url = httpServletRequest.getRequestURI();
        Map<String, String> map = new HashMap<String, String>();
        map.put(url, params);
        String nowUrlParams = map.toString();//

        Object preUrlParams = httpServletRequest.getSession().getAttribute("repeatData");
        if (preUrlParams == null) { // 如果上一个数据为null,表示还没有访问页面
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
            return false;
        } else { // 否则，已经访问过页面
            if (preUrlParams.toString().equals(nowUrlParams)) { // 如果上次url+数据和本次url+数据相同，则表示城府添加数据
                logger.error(" resubmit warn : urlParams not match ! nowUrlParams：" + nowUrlParams);
                return true;
            } else { // 如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }

        }
    }

}
