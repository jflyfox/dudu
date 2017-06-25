package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.dao.MenuMapper;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.dudu.module.system.service.IMenuService;
import com.jflyfox.dudu.module.system.service.IRolemenuService;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.dudu.module.system.service.IUserroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 菜单 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, SysMenu> implements IMenuService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserroleService userroleService;
    @Autowired
    private IRolemenuService rolemenuService;

    public List<SysMenu> listUserMenu(Query query) {
        // 权限处理,获取所有菜单信息；
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<>();
        menuWrapper.orderBy("sort,id desc");
        List<SysMenu> menuList = selectList(menuWrapper);

        final Map<Long, List<SysMenu>> menus;
        Function<SysMenu, Long> groupingByFunc = (SysMenu s) -> s.getParentid();
//        if (userinfo.getUsertype() == 1) {
            menus = menuList.stream().collect(Collectors.groupingBy(groupingByFunc));
//        } else {
//            // 获取用户角色
//            Wrapper<SysUserRole> userroleWrapper = new EntityWrapper<>();
//            userroleWrapper.eq("userid", userinfo.getId());
//            List<SysUserRole> userRoleList = userroleService.selectList(userroleWrapper);
//            List<Long> roleidList = userRoleList.stream().map(userRole -> userRole.getRoleid()).collect(Collectors.toList());
//
//            // 获取用户菜单
//            Wrapper<SysRoleMenu> rolemenuWrapper = new EntityWrapper<>();
//            rolemenuWrapper.in("roleid", roleidList);
//            List<SysRoleMenu> roleMenuList = rolemenuService.selectList(rolemenuWrapper);
//            List<Long> menuidList = roleMenuList.stream().map(roleMenu -> roleMenu.getMenuid()).collect(Collectors.toList());
//
//            // 只展示用户能访问的数据
//            menus = menuList.stream().filter(menu -> menuidList.contains(menu.getId())).collect(Collectors.groupingBy(groupingByFunc));
//        }

        // 4. 按照parentid分组，返回菜单数据（key改为字符串便于前台获取，key为0的为跟目录）
        if (menus.get(0L) == null)
            return null;

        List<SysMenu> userMenuList = menus.get(0L);
        userMenuList.forEach(menu -> {
            menu.setChilds(menus.get(menu.getId()));
        });

        return userMenuList;
    }

    public PageInfo<SysMenu> selectMenuPage(Query query) {
        // DbContextHolder.setDbType(DBTypeEnum.app);
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(baseMapper.selectMenuPage(query));
        return pageInfo;
    }

    public String selectMenu(long selected) {
        return selectMenu(selected, 0);
    }

    public String selectMenu(long selected, long selfId) {
        String where = " 1 = 1 ";
        if (selfId > 0) {
            where += "and id !=" + selfId;
        }

        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.where(where).eq("parentid", 0).orderBy("sort,create_time desc");
        List<SysMenu> list = selectList(wrapper);

        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (SysMenu menu : list) {
                long parentId = menu.getParentid();
                if (parentId == 0L) {
                    long id = menu.getId();
                    sb.append("<option value=\"");
                    sb.append(id);
                    sb.append("\" ");
                    boolean flag = (id == selected);
                    sb.append(flag ? "selected" : "");
                    sb.append(">");
                    sb.append(menu.getName());
                    sb.append("</option>");

                    sb.append(recursionTree(id, selected, 1, list));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 递归树
     * <p>
     * 2017年3月14日 上午10:39:29
     * flyfox 330627517@qq.com
     *
     * @return
     */
    private String recursionTree(long rootId, long selected, int level, List<SysMenu> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }

        // 级别太多了
        if (level >= 20) {
            return "";
        }

        StringBuffer sb = new StringBuffer("");
        for (SysMenu menu : list) {
            long parentId = menu.getParentid();
            if (parentId == rootId) {
                long id = menu.getId();
                sb.append("<option value=\"");
                sb.append(id);
                sb.append("\" ");
                boolean flag = (id == selected);
                sb.append(flag ? "selected" : "");
                sb.append(">");
                for (int i = 0; i < level; i++) {
                    if (i == (level - 1)) {
                        sb.append("&nbsp;|--");
                    } else {
                        sb.append("&nbsp;&nbsp;&nbsp;");
                    }
                }
                sb.append(menu.getName());
                sb.append("</option>");

                sb.append(recursionTree(id, selected, (level + 1), list));
            }
        }

        return sb.toString();
    }
}