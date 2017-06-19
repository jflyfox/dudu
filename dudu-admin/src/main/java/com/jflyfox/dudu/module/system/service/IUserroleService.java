package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysUserRole;

/**
 * 用户和角色关联 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface IUserroleService extends IBaseService<SysUserRole> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysUserRole> selectUserrolePage(Query query);

}