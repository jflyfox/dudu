package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.dudu.module.system.dao.RolemenuMapper;
import com.jflyfox.dudu.module.system.service.IRolemenuService;

/**
 * 角色和菜单关联 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class RolemenuServiceImpl extends BaseServiceImpl<RolemenuMapper, SysRoleMenu> implements IRolemenuService {

    public Page<SysRoleMenu> selectRolemenuPage(Page<SysRoleMenu> page, Wrapper<SysRoleMenu> wrapper) {
        page.setRecords(baseMapper.selectRolemenuPage(page, wrapper));
        return page;
    }
}