package com.jflyfox.dudu.component.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 计时器。用于记录请求执行时间。
 */
public class TimerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(TimerInterceptor.class);
    public static final String TIMER_BEGIN = "_timerBegin";
    public static final NumberFormat FORMAT = new DecimalFormat("0.000");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long begin = System.currentTimeMillis();
        request.setAttribute(TIMER_BEGIN, begin);
        if (logger.isDebugEnabled()) {
            String uri = ((HttpServletRequest) request).getRequestURI();
            logger.debug("Processed start. URI={}", uri);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (logger.isDebugEnabled()) {
            Long begin = (Long) request.getAttribute(TIMER_BEGIN);
            if (begin != null) {
                long end = System.currentTimeMillis();
                BigDecimal processed = new BigDecimal(end - begin).divide(new BigDecimal(1000));
                String uri = ((HttpServletRequest) request).getRequestURI();
                logger.debug("Processed end. time: {} second(s). URI={}", FORMAT.format(processed), uri);
            }
        }
    }

}
