package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.system.common.SystemConstants;
import com.jflyfox.dudu.module.system.dao.UserMapper;
import com.jflyfox.dudu.module.system.model.SysRole;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.dudu.module.system.service.IRoleService;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.dudu.module.system.service.IUserroleService;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, SysUser> implements IUserService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserroleService userroleService;

    public Page<SysUser> selectUserPage(Page<SysUser> page, Wrapper<SysUser> wrapper) {
        page.setRecords(baseMapper.selectUserPage(page, wrapper));
        return page;
    }

    @Override
    public List<SysRole> getSysRoleList() {
        Wrapper<SysRole> roleWraper = new EntityWrapper<>();
        roleWraper.eq("status", SystemConstants.SHOW);
        roleWraper.orderBy("sort");
        List<SysRole> roleList = roleService.selectList(roleWraper);
        return roleList;
    }

    @Override
    public String getRoleids(Long userid) {
        Wrapper<SysUserRole> userroleWraper = new EntityWrapper<>();
        userroleWraper.eq("userid", userid);
        List<SysUserRole> userroleList = userroleService.selectList(userroleWraper);
        String roleids = userroleList.stream().map(userRole -> userRole.getRoleid() + "").collect(Collectors.joining(","));
        return roleids;
    }

    @Override
    public boolean saveUserRole(Long userid, String roleids) {
        SqlRunner.db().delete("delete from sys_user_role where userid = " + userid);
        if (StrUtils.isNotEmpty(roleids)) {
            String[] arr = roleids.split(",");
            Arrays.asList(arr).forEach(roleid -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleid(NumberUtils.parseLong(roleid));
                userRole.setUserid(userid);
                userroleService.insert(userRole);
            });
        }
        return true;
    }
}