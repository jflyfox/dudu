package com.jflyfox.dudu.component.config;

import com.jflyfox.dudu.component.shiro.RetryLimitCredentialsMatcher;
import com.jflyfox.dudu.component.shiro.ShiroDbRealm;
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

    @Bean("cacheManager")
    public CacheManager shiroEhCacheManager() throws IOException {
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
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login");
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
//        filters.put("backSite", new BackSiteFilter(beanFactory));
//        filters.put("authc", new CmsAuthenticationFilter(beanFactory));
//        filters.put("user", new CmsUserFilter());
//        filters.put("logout", new CmsLogoutFilter(beanFactory));
        factoryBean.setFilters(filters);
//        Map<String, String> filterChainDefinitionMap = propertiesHelper()
//                .getSortedMap("shiroFilterChainDefinitionMap.");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//         filterChainDefinitionMap.put("/*", "anon");
//         filterChainDefinitionMap.put("*", "anon");
//         filterChainDefinitionMap.put("*.jsp", "anon");
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
//         filterChainDefinitionMap.put("/my", "user");
//         filterChainDefinitionMap.put("/my/**", "user");
//         filterChainDefinitionMap.put("/cmscp/", "backSite,anon");
//         filterChainDefinitionMap.put("/cmscp/index.do", "backSite,anon");
//         filterChainDefinitionMap.put("/cmscp/login.do", "backSite,authc");
//         filterChainDefinitionMap.put("/cmscp/logout.do", "backSite,logout");
//         filterChainDefinitionMap.put("/cmscp/**", "backSite,user");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    // @Bean
    // public FilterRegistrationBean timerFilterRegistrationBean() {
    // FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
    // filterRegistration.setFilter(new TimerFilter());
    // filterRegistration.setEnabled(true);
    // filterRegistration.addUrlPatterns("/*");
    // filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
    // return filterRegistration;
    // }

//    @Bean
//    public FilterRegistrationBean jspDispatcherFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new JspDispatcherFilter());
//        filterRegistration.setEnabled(true);
//        filterRegistration.addInitParameter("prefix", "/jsp");
//        filterRegistration.addUrlPatterns("*.jsp");
//        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//        return filterRegistration;
//    }
//
//    @Bean
//    public FilterRegistrationBean openEntityManagerInViewFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new OpenEntityManagerInViewFilter());
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }

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
