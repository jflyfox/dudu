package com.jflyfox.dudu.component.config;

import com.jflyfox.dudu.component.common.Constants;
import com.jflyfox.dudu.component.interceptor.CommonAttrInterceptor;
import com.jflyfox.dudu.component.interceptor.LoginInterceptor;
import com.jflyfox.dudu.component.interceptor.PermissionInterceptor;
import com.jflyfox.dudu.component.interceptor.SQLInjectInterceptor;
import com.jflyfox.dudu.component.interceptor.resubmit.FormTokenInterceptor;
import com.jflyfox.dudu.component.interceptor.resubmit.SameUrlDataInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    public void addInterceptors(final InterceptorRegistry registry) {
        logger.warn("####addInterceptors init start......");

        // SQL注入拦截器拦截
//        registry.addInterceptor(getSQLInjectInterceptor()).addPathPatterns("/**");
        // 后台登陆拦截
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns(Constants.getBackPatternsList());
        // 权限拦截器拦截器拦截
        registry.addInterceptor(getPermissionInterceptor()).addPathPatterns(Constants.getBackPatternsList());
        // 后台重复提交拦截Token
        registry.addInterceptor(getFormTokenInterceptor()).addPathPatterns("/**");
        // 后台重复提交拦截URL Data
        registry.addInterceptor(getSameUrlDataInterceptor()).addPathPatterns(Constants.getBackPatternsList());
        // 公共参数拦截器拦截
        registry.addInterceptor(getCommonAttrInterceptor()).addPathPatterns("/**");

        logger.warn("####addInterceptors init finish.");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/project/**").addResourceLocations("classpath:/project/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public SQLInjectInterceptor getSQLInjectInterceptor() {
        return new SQLInjectInterceptor();
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public PermissionInterceptor getPermissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Bean
    public FormTokenInterceptor getFormTokenInterceptor() {
        return new FormTokenInterceptor();
    }

    @Bean
    public SameUrlDataInterceptor getSameUrlDataInterceptor() {
        return new SameUrlDataInterceptor();
    }

    @Bean
    public CommonAttrInterceptor getCommonAttrInterceptor() {
        return new CommonAttrInterceptor();
    }
}
