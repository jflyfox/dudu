package com.jflyfox.dudu.component.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/20.
 */
public class SessionUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;

    private Long id;
    private String loginName;
    private String name;
    private Set<String> urlSet;
    private Set<String> roles;

    public SessionUser() {
    }

    public SessionUser(Long id) {
        this.id = id;
    }

    public SessionUser(String loginName) {
        this.loginName = loginName;
    }

    public SessionUser(Long id, String loginName, String name, Set<String> urlSet) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.urlSet = urlSet;
    }

    public SessionUser(Long id, String loginName, String name, Set<String> urlSet, Set<String> roles) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.urlSet = urlSet;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUrlSet() {
        return urlSet;
    }

    public void setUrlSet(Set<String> urlSet) {
        this.urlSet = urlSet;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getLoginName() {
        return loginName;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

}
