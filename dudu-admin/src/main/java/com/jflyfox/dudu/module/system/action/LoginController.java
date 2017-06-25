package com.jflyfox.dudu.module.system.action;

import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.util.ImageCode;
import com.jflyfox.dudu.module.system.model.LogOperType;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.permission.InvalidPermissionStringException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * loginController
 */
@RestController
@RequestMapping(value = "/admin")
public class LoginController extends BaseController {

    public static final String loginPage = "/pages/admin/login.html";
    public static final String homePage = "/pages/admin/home/home.html";
    public static final String homePath = "redirect:/admin/home";

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        if (!validSessionUser()) {
            // 如果session存在，不再验证
            view.setViewName(loginPage);
            return view;
        }

        view.setViewName(homePath);
        return view;
    }

    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView view = new ModelAndView();
        if (!validSessionUser()) {
            // 如果session存在，不再验证
            view.setViewName(loginPage);
            return view.addObject("msg", "请先进行登录！");
        }

        view.setViewName(homePage);
        return view;
    }

    /**
     * 登录
     *
     * @author flyfox 2013-11-11
     */
    @RequestMapping(value = "/login")
    public Object login(String username, String password, String imageCode) {
        // 获取验证码
        String checkCode = getSessionAttr(ImageCode.class.getName());

        // 这种情况一般都是直接访问login了~！~
        if (StrUtils.isEmpty(imageCode)) {
            return fail("验证码不能为空！");
        }

        if (StrUtils.isEmpty(checkCode) || !checkCode.equalsIgnoreCase(imageCode)) {
            return fail("验证码错误！");
        }

        // 初始化数据字典Map
        if (StrUtils.isEmpty(username)) {
            return fail("用户名不能为空！");
        } else if (StrUtils.isEmpty(password)) {
            return fail("密码不能为空！");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 使用cookie
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return fail("账号不存在！");
        } catch (DisabledAccountException e) {
            return fail("账号未启用！");
        } catch (IncorrectCredentialsException e) {
            return fail("密码错误！");
        } catch (InvalidPermissionStringException e) {
            if ("login".equals(e.getPermissionString())) {
                return fail("您没有登录权限，请您重新输入!");
            } else {
                return fail("您没有相应的权限，请您重新登录!");
            }
        } catch (Throwable e) {
            logger.error("认证异常！", e);
            return fail("认证异常！");
        }

        // 添加日志
        userService.saveSystemLog(getSessionUser().getId(), LogOperType.LOGIN);

        return success();
    }

    /**
     * 登出
     *
     * @author flyfox 2013-11-13
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        if (validSessionUser()) {
            // 添加日志
            userService.saveSystemLog(getSessionUser().getId(), LogOperType.LOGOUT);
            // 登出
            subject.logout();
        }

        ModelAndView view = new ModelAndView(loginPage);
        view.addObject("msg", "您已退出");
        return view;
    }

    @RequestMapping(value = "/imagecode")
    public ModelAndView imagecode(HttpServletRequest request, HttpServletResponse response) {
        try {
            new ImageCode().doGet(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImageCode iamgecode = new ImageCode();
            iamgecode.doGet(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/trans/{path}")
    public String trans(@PathVariable String path) {
        if (StrUtils.isEmpty(path)) {
            path = Config.getStr("PAGES.TRANS");
        } else if (path.equals("auth")) {
            path = "/pages/error/trans_no_auth.html";
        }
        return path;
    }
}
