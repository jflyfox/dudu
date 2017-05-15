package com.jflyfox.dudu.module.admin.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.IBaseService;
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
     * @param page
     * @param wrapper
     * @return
     */
    Page<TbContact> selectContactPage(Page<TbContact> page, Wrapper<TbContact> wrapper);

}