package com.jflyfox.dudu.component.config;

import com.jflyfox.dudu.component.common.Constants;
import com.jflyfox.dudu.component.filter.JspDispatcherFilter;
import com.jflyfox.dudu.component.shiro.RetryLimitCredentialsMatcher;
import com.jflyfox.dudu.component.shiro.ShiroDbRealm;
import com.jflyfox.dudu.component.shiro.filter.BackFilter;
import com.jflyfox.dudu.component.shiro.filter.LoginUserFilter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO 差shiro地址和权限拦截以及redis缓存
 * Created by flyfox 369191470@qq.com on 2017/5/16.
 */
@Configuration
public class ShiroConfig {

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager(Realm shiroRealm
            , CacheManager cacheManager
            , RememberMeManager rememberMeManager) throws IOException {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    @Bean("shiroRealm")
    public Realm shiroRealm(CacheManager cacheManager, CredentialsMatcher credentialsMatcher) {
        // TODO credentialsMatcher带完善内部Cache
        ShiroDbRealm realm = new ShiroDbRealm(cacheManager, null);
        realm.setAuthorizationCachingEnabled(false);
        return realm;
    }

    @Bean("shiroCacheManager")
    public CacheManager shiroCacheManager() throws IOException {
        return new MemoryConstrainedCacheManager();
    }

    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(CacheManager cacheManager) throws IOException {
        return new RetryLimitCredentialsMatcher(cacheManager);
    }

    @Bean("rememberMeManager")
    public RememberMeManager rememberMeManager(Cookie rememberMeCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }

    @Bean("rememberMeCookie")
    public Cookie rememberMeCookie() {
        Cookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(2592000); // 30天
        return cookie;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) throws IOException {
//        anon  不需要认证
//        authc 需要认证
//        user  验证通过或RememberMe登录的都可以
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login"); // 前台Login
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("back", new BackFilter());
//        filters.put("authc", new AuthenticationFilter());
        filters.put("user", new LoginUserFilter());
//        filters.put("logout", new LogoutFilter());
        factoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/*", "anon");
        filterChainDefinitionMap.put("*.jsp", "anon");

        // 不认证后台路径
        for (int i = 0; i < Constants.BACK_ANON_PATHS.length; i++) {
            filterChainDefinitionMap.put("/" + Constants.BACK_ANON_PATHS[i], "anon");
        }
        // 后台认证路径
        for (int i = 0; i < Constants.BACK_PATHS.length; i++) {
            String urlKey = "/" + Constants.BACK_PATHS[i] + "/**";
            filterChainDefinitionMap.put(urlKey, "back,user");
        }

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean
    public FilterRegistrationBean jspDispatcherFilterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new JspDispatcherFilter());
        filterRegistration.setEnabled(true);
        filterRegistration.addInitParameter("prefix", "/jsp");
        filterRegistration.addUrlPatterns("*.jsp");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean shiroFilterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
}
