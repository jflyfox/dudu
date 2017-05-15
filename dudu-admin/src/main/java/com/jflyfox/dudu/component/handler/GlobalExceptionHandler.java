package com.jflyfox.dudu.component.handler;

import com.jflyfox.dudu.component.model.ErrorInfo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/4/12.
 */
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error/500";

    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
