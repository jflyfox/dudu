package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.dudu.module.system.dao.UserroleMapper;
import com.jflyfox.dudu.module.system.service.IUserroleService;

/**
 * 用户和角色关联 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class UserroleServiceImpl extends BaseServiceImpl<UserroleMapper, SysUserRole> implements IUserroleService {

    public Page<SysUserRole> selectUserrolePage(Page<SysUserRole> page, Wrapper<SysUserRole> wrapper) {
        page.setRecords(baseMapper.selectUserrolePage(page, wrapper));
        return page;
    }
}