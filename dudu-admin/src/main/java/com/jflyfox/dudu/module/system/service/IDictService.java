package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysDict;

/**
 * 数据字典主表 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IDictService extends IBaseService<SysDict> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysDict> selectDictPage(Query query);

}