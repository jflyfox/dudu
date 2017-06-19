package com.jflyfox.dudu.module.admin.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.model.TbContact;

/**
 * 联系人 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
public interface IContactService extends IBaseService<TbContact> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<TbContact> selectContactPage(Query query);

}