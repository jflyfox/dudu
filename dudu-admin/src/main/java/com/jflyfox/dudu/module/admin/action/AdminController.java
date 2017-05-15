package com.jflyfox.dudu.module.admin.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.component.util.EncryptUtils;
import com.jflyfox.dudu.component.util.ImageCode;
import com.jflyfox.dudu.module.system.model.LogOperType;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.util.Config;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.encrypt.Md5Utils;
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
 * adminController
 */
@RestController
@RequestMapping(value = "/admin")     // 通过这里配置使下面的映射都在/users下，可去除
public class AdminController extends BaseController {

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
            return view;
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
    public ModelAndView login(String username, String password, String imageCode) {
        ModelAndView view = new ModelAndView(loginPage);
        // 获取验证码
        String checkCode = getSessionAttr(ImageCode.class.getName());

        // 这种情况一般都是直接访问login了~！~
        if (StrUtils.isEmpty(imageCode)) {
            return view;
        }

        if (StrUtils.isEmpty(checkCode) || !checkCode.equalsIgnoreCase(imageCode)) {
            view.addObject("msg", "验证码错误！");
            return view;
        }

        // 初始化数据字典Map
        if (StrUtils.isEmpty(username)) {
            view.addObject("msg", "用户名不能为空！");
            return view;
        } else if (StrUtils.isEmpty(password)) {
            view.addObject("msg", "密码不能为空！");
            return view;
        }
        // String encryptPassword = JFlyFoxUtils.passwordEncrypt(password); //
        // 前台md5加密
        String encryptPassword = password;

        SysUser userWhere = new SysUser();
        userWhere.setUsername(username);
        SysUser user = userService.selectOne(new EntityWrapper<SysUser>(userWhere));
        if (user == null || user.getId() <= 0) {
            view.addObject("msg", "认证失败，请您重新输入!");
            return view;
        }

        String md5Password = "";
        try {
            String userPassword = user.getPassword();
            String decryptPassword = EncryptUtils.passwordDecrypt(userPassword);
            md5Password = new Md5Utils().getMD5(decryptPassword).toLowerCase();
        } catch (Exception e) {
            logger.error("认证异常", e);
            view.addObject("msg", "认证异常，请您重新输入!");
            return view;
        }

        if (!md5Password.equals(encryptPassword)) {
            view.addObject("msg", "认证错误，请您重新输入!");
            return view;
        }

        if (!(user.getUsertype() == 1 || user.getUsertype() == 2)) {
            view.addObject("msg", "您没有登录权限，请您重新输入!");
            return view;
        }

        // 创建session
        SessionUser sessionUser = new SessionUser();
        sessionUser.setId(user.getId());
        setSessionUser(sessionUser);

        // TODO 首页设置 第一个页面跳转
        String tmpMainPage = "";

        if (tmpMainPage == null) {
            view.addObject("msg", "没有权限，请联系管理员!");
            return view;
        }

        // 添加日志
        userService.saveSystemLog(user.getId(), LogOperType.LOGIN);

        view.setViewName(homePath);
        return view;
    }

    /**
     * 登出
     *
     * @author flyfox 2013-11-13
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        SessionUser user = getSessionUser();
        if (user != null) {
            // 添加日志
            userService.saveSystemLog(user.getId(), LogOperType.LOGOUT);
            // 删除session
            removeSessionUser();
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
