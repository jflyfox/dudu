package com.jflyfox.dudu.module.system.service;

import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.module.system.model.SysUser;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/22.
 */
public interface ISessionAttrService {

    /**
     * 通过用户信息返回SessionUser
     *
     * @param user 用户信息
     * @return SessionUser
     */
    SessionUser newSessionUser(SysUser user);

    /**
     * 通过sessionUser获取用户信息
     *
     * @param sessionUser session信息
     * @return SysUser
     */
    SysUser getSysUser(SessionUser sessionUser);

    /**
     * 通过用户ID获取用户信息
     *
     * @param userid 用户ID
     * @return SysUser
     */
    SysUser getSysUser(long userid);
}
