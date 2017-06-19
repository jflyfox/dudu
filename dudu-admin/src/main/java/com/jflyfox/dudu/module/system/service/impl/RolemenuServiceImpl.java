package com.jflyfox.dudu.module.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.dudu.module.system.dao.RolemenuMapper;
import com.jflyfox.dudu.module.system.service.IRolemenuService;

/**
 * 角色和菜单关联 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
@Service
public class RolemenuServiceImpl extends BaseServiceImpl<RolemenuMapper, SysRoleMenu> implements IRolemenuService {

    public PageInfo<SysRoleMenu> selectRolemenuPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysRoleMenu> pageInfo = new PageInfo<SysRoleMenu>(baseMapper.selectRolemenuPage(query));
        return pageInfo;
    }
}