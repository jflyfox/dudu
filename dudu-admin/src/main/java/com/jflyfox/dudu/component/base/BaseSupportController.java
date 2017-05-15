package com.jflyfox.dudu.component.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器 基础类
 *
 * Created by flyfox 369191470@qq.com on 2017/4/20.
 */
public class BaseSupportController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;
    @Autowired
    protected ServletContext application;

    /**
     * Return HttpServletRequest. Do not use HttpServletRequest Object in constructor of Controller
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Return HttpServletResponse. Do not use HttpServletResponse Object in constructor of Controller
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Return HttpSession.
     */
    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * Return HttpSession.
     *
     * @param create a boolean specifying create HttpSession if it not exists
     */
    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }

    /**
     * Return a Object from session.
     *
     * @param key a String specifying the key of the Object stored in session
     */
    public <T> T getSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * Store Object to session.
     *
     * @param key   a String specifying the key of the Object stored in session
     * @param value a Object specifying the value stored in session
     */
    public BaseSupportController setSessionAttr(String key, Object value) {
        request.getSession(true).setAttribute(key, value);
        return this;
    }

    /**
     * Remove Object in session.
     *
     * @param key a String specifying the key of the Object stored in session
     */
    public BaseSupportController removeSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.removeAttribute(key);
        return this;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    /**
     * Get cookie value by cookie name.
     */
    public String getCookie(String name) {
        return getCookie(name, null);
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Integer.
     */
    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    /**
     * Get cookie value by cookie name and convert to Long.
     */
    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    /**
     * Get cookie object by cookie name.
     */
    public Cookie getCookieObject(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if (cookie.getName().equals(name))
                    return cookie;
        return null;
    }

    /**
     * Get all cookie objects.
     */
    public Cookie[] getCookieObjects() {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    /**
     * Set Cookie.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public BaseSupportController setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    /**
     * Set Cookie.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     */
    public BaseSupportController setCookie(String name, String value, int maxAgeInSeconds) {
        return doSetCookie(name, value, maxAgeInSeconds, null, null, null);
    }

    /**
     * Set Cookie to response.
     */
    public BaseSupportController setCookie(Cookie cookie) {
        response.addCookie(cookie);
        return this;
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public BaseSupportController setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     */
    public BaseSupportController setCookie(String name, String value, int maxAgeInSeconds, String path) {
        return doSetCookie(name, value, maxAgeInSeconds, path, null, null);
    }

    /**
     * Set Cookie to response.
     * @param name cookie name
     * @param value cookie value
     * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
     * @param path see Cookie.setPath(String)
     * @param domain the domain name within which this cookie is visible; form is according to RFC 2109
     * @param isHttpOnly true if this cookie is to be marked as HttpOnly, false otherwise
     */
    public BaseSupportController setCookie(String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    /**
     * Remove Cookie.
     */
    public BaseSupportController removeCookie(String name) {
        return doSetCookie(name, null, 0, null, null, null);
    }

    /**
     * Remove Cookie.
     */
    public BaseSupportController removeCookie(String name, String path) {
        return doSetCookie(name, null, 0, path, null, null);
    }

    /**
     * Remove Cookie.
     */
    public BaseSupportController removeCookie(String name, String path, String domain) {
        return doSetCookie(name, null, 0, path, domain, null);
    }

    private BaseSupportController doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        // set the default path value to "/"
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }
        response.addCookie(cookie);
        return this;
    }
}
