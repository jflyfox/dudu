package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.model.SysRole;

import java.util.List;

/**
 * 角色 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IRoleService extends IBaseService<SysRole> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysRole> selectRolePage(Query query);

    /**
     * 根据父节点获取List
     * <p>
     * 2015年4月28日 上午11:43:07 flyfox 369191470@qq.com
     *
     * @param parentid
     * @return
     */
    List<SysMenu> getListByParentid(long parentid);

    /**
     * 获取菜单ID字符串
     *
     * @return
     */
    String getMenuids(Long roleid);

    /**
     * 保存角色和菜单绑定信息
     *
     * @return
     */
    boolean saveRoleMenu(Long roleid, String menuids);
}