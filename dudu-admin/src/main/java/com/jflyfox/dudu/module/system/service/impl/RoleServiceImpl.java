package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.common.SystemConstants;
import com.jflyfox.dudu.module.system.dao.RoleMapper;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.model.SysRole;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.dudu.module.system.service.IMenuService;
import com.jflyfox.dudu.module.system.service.IRoleService;
import com.jflyfox.dudu.module.system.service.IRolemenuService;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, SysRole> implements IRoleService {

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IRolemenuService roleMenuService;

    public PageInfo<SysRole> selectRolePage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(baseMapper.selectRolePage(query));
        return pageInfo;
    }

    public List<SysMenu> getListByParentid(long parentid) {
        Wrapper<SysMenu> menuWrapper = new EntityWrapper<SysMenu>();
        menuWrapper.eq("parentid", parentid);
        menuWrapper.eq("status", SystemConstants.SHOW);
        menuWrapper.orderBy("sort");
        List<SysMenu> rolemenuList = menuService.selectList(menuWrapper);
        return rolemenuList;
    }

    public String getMenuids(Long roleid) {
        Wrapper<SysRoleMenu> rolemenuWraper = new EntityWrapper<SysRoleMenu>();
        rolemenuWraper.eq("roleid", roleid);
        List<SysRoleMenu> rolemenuList = roleMenuService.selectList(rolemenuWraper);
        String menuids = rolemenuList.stream().map(roleMenu -> roleMenu.getMenuid() + "").collect(Collectors.joining(","));
        return menuids;
    }

    public boolean saveRoleMenu(Long roleid, String menuids) {
        SqlRunner.db().delete("delete from sys_role_menu where roleid = " + roleid);
        if (StrUtils.isNotEmpty(menuids)) {
            String[] arr = menuids.split(",");
            Arrays.asList(arr).forEach(menuid -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleid(roleid);
                roleMenu.setMenuid(NumberUtils.parseLong(menuid));
                roleMenuService.insert(roleMenu);
            });
        }
        return true;
    }
}