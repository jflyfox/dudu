package com.jflyfox.dudu.component.shiro.filter;

import com.jflyfox.dudu.component.common.Constants;
import com.jflyfox.dudu.component.util.DuduWebUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/18.
 */
public class LoginUserFilter extends UserFilter {
    private String backLoginUrl = Constants.BACK_LOGIN_URL;

    @Override
    protected void redirectToLogin(ServletRequest req, ServletResponse resp)
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String loginUrl = getLoginUrl();
        if (DuduWebUtils.isBack(request)) {
            loginUrl = getBackLoginUrl();
        }

        WebUtils.issueRedirect(request, response, loginUrl);
    }

    public String getBackLoginUrl() {
        return backLoginUrl;
    }

    public void setBackLoginUrl(String backLoginUrl) {
        this.backLoginUrl = backLoginUrl;
    }

}
