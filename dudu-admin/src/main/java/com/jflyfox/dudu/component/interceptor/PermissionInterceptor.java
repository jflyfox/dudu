package com.jflyfox.dudu.component.interceptor;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.component.util.DuduWebUtils;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.dudu.module.system.service.IMenuService;
import com.jflyfox.dudu.module.system.service.IRolemenuService;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.dudu.module.system.service.IUserroleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/2.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IUserroleService userroleService;
    @Autowired
    private IRolemenuService rolemenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 不是后台的，跳过
        if (!DuduWebUtils.isBack(request)) {
            return true;
        }

        // 是后台忽略路径，跳过
        if (DuduWebUtils.isBackAnon(request)) {
            return true;
        }

        // 权限处理,获取所有菜单信息；
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<>();
        menuWrapper.orderBy("sort,id desc");
        List<SysMenu> menuList = menuService.selectList(menuWrapper);

        // 1. 拿到session中的userid；
        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getPrincipal();
        // 用户没有登录不处理
        if (sessionUser == null || sessionUser.getId() == null)
            return true;

        // 2. 根据userid，获取用户信息；
        SysUser userinfo = userService.selectById(sessionUser.getId());
        request.setAttribute("userinfo", userinfo);
        // 3. 根据userid，获取权限；管理员全部菜单，普通用户获取有权限的菜单；
        Map<String, List<SysMenu>> menus = null;
        Function<SysMenu, String> function = (SysMenu s) -> s.getParentid() + "";
        if (userinfo.getUsertype() == 1) {
            menus = menuList.stream().collect(Collectors.groupingBy(function));
        } else {
            // 获取用户角色
            Wrapper<SysUserRole> userroleWrapper = new EntityWrapper<>();
            userroleWrapper.eq("userid", userinfo.getId());
            List<SysUserRole> userRoleList = userroleService.selectList(userroleWrapper);
            List<Long> roleidList = userRoleList.stream().map(userRole -> userRole.getRoleid()).collect(Collectors.toList());

            // 获取用户菜单
            Wrapper<SysRoleMenu> rolemenuWrapper = new EntityWrapper<>();
            rolemenuWrapper.in("roleid", roleidList);
            List<SysRoleMenu> roleMenuList = rolemenuService.selectList(rolemenuWrapper);
            List<Long> menuidList = roleMenuList.stream().map(roleMenu -> roleMenu.getMenuid()).collect(Collectors.toList());

            // 只展示用户能访问的数据
            menus = menuList.stream().filter(menu -> menuidList.contains(menu.getId())).collect(Collectors.groupingBy(function));
        }

        // 4. 按照parentid分组，返回菜单数据（key改为字符串便于前台获取，key为0的为跟目录）
        if (menus.get("0") == null)
            menus = null;
        request.setAttribute("menus", menus);

        return true;
    }

}
