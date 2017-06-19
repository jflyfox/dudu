package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysDictDetail;

/**
 * 数据字典 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface IDictdetailService extends IBaseService<SysDictDetail> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysDictDetail> selectDictdetailPage(Query query);

}