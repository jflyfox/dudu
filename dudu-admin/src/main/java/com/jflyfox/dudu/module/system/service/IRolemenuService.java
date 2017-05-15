package com.jflyfox.dudu.module.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;

/**
 * 角色和菜单关联 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IRolemenuService extends IBaseService<SysRoleMenu> {

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysRoleMenu> selectRolemenuPage(Page<SysRoleMenu> page, Wrapper<SysRoleMenu> wrapper);

}