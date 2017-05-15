package com.jflyfox.dudu.component.base;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 自定义的全局错误页面
 *
 * @author darren
 * @date 2016年12月29日 上午9:38:33
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ModelAndView getErrorPath(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        // 获取异常信息
        @SuppressWarnings("rawtypes")
        Class exceptionType = (Class) request.getAttribute("javax.servlet.error.exception_type");
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        Map<String, Object> error = new HashMap<>();
        if (statusCode != null)
            error.put("code", statusCode);
        if (exception != null)
            error.put("msg", exception.getMessage());
        if (exceptionType != null)
            error.put("exception", exceptionType.getName());
        if (servletName != null)
            error.put("servlet", servletName);
        if (requestUri != null)
            error.put("uri", requestUri);
        //防止部分浏览器、路由器（如小米）等劫持不显示自己的错误页面，强制将code设置为200
        //但这样ajax就无法检测错误状态，有待商榷
        response.setStatus(HttpServletResponse.SC_OK);
        mv.addObject("error", error);
        mv.setViewName("/error.html");
        return mv;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
