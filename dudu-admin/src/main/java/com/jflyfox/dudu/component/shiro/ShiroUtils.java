package com.jflyfox.dudu.component.shiro;

import com.jflyfox.dudu.component.model.SessionUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/16.
 */
public class ShiroUtils {

    /**
     * 验证Session User是否合法
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public boolean validSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        return (subject.isAuthenticated() || subject.isRemembered());
    }

    /**
     * 获取Session User
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public SessionUser getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        // 没有登录
        if (!(subject.isAuthenticated() || subject.isRemembered())) {
            return new SessionUser();
        }

        SessionUser sessionUser = (SessionUser) subject.getPrincipal();
        return sessionUser;
    }
}
