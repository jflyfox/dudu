package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysRole;
import com.jflyfox.dudu.module.system.model.SysUser;

import java.util.List;

/**
 * 用户 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IUserService extends IBaseService<SysUser> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysUser> selectUserPage(Query query);

    /**
     * 获取角色列表
     *
     * @return
     */
    List<SysRole> getSysRoleList();

    /**
     * 获取角色id字符串
     *
     * @return
     */
    String getRoleids(Long userid);

    /**
     * 保存用户和角色绑定信息
     *
     * @return
     */
    boolean saveUserRole(Long userid, String roleids);

}