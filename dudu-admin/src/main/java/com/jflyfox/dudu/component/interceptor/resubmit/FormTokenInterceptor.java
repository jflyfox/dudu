package com.jflyfox.dudu.component.interceptor.resubmit;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */

import com.jflyfox.dudu.component.model.resubmit.FormToken;
import com.jflyfox.dudu.component.util.DuduRespUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * form重复提交，token拦截；可防止CSRF
 * <p>
 * <input type="hidden" name="formToken" value="${formToken}" />
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(FormTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FormToken annotation = method.getAnnotation(FormToken.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession(false).setAttribute("formToken", UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        // 返回json提示
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json; charset=utf-8");
                        response.getWriter().write(DuduRespUtils.fail(null, "数据重复相同或请求异常").toJSONString());

                        return false;
                    }
                    request.getSession(false).removeAttribute("formToken");
                }
            }
            return true;
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("formToken");
        if (serverToken == null) {
            logger.error(" resubmit warn : serverToken is null ! ");
            return true;
        }
        String clinetToken = request.getParameter("formToken");
        if (clinetToken == null) {
            logger.error(" resubmit warn : clinetToken is null ! ");
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            logger.error(" resubmit warn : token not match ! ");
            return true;
        }
        return false;
    }
}
