package com.jflyfox.dudu.module.system.service.impl;

import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.service.ISessionAttrService;
import com.jflyfox.dudu.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 统一session属性处理服务层
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/22.
 */
@Service
public class SessionAttrServiceImpl implements ISessionAttrService {

    @Autowired
    IUserService userService;

    public SessionUser newSessionUser(SysUser user) {
        if (user == null) {
            return null;
        }

        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(user.getId());
        return sessionUser;
    }

    public SysUser getSysUser(SessionUser sessionUser) {
        if (sessionUser == null) {
            return null;
        }
        return userService.selectById(sessionUser.getId());
    }

    public SysUser getSysUser(long userid) {
        if (userid <= 0) {
            return null;
        }

        return userService.selectById(userid);
    }

}
