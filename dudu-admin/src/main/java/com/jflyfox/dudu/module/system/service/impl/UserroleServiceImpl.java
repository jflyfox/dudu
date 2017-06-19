package com.jflyfox.dudu.module.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.dudu.module.system.dao.UserroleMapper;
import com.jflyfox.dudu.module.system.service.IUserroleService;

/**
 * 用户和角色关联 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
@Service
public class UserroleServiceImpl extends BaseServiceImpl<UserroleMapper, SysUserRole> implements IUserroleService {

    public PageInfo<SysUserRole> selectUserrolePage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysUserRole> pageInfo = new PageInfo<SysUserRole>(baseMapper.selectUserrolePage(query));
        return pageInfo;
    }
}