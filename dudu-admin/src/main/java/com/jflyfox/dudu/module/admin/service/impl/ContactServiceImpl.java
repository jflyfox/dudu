package com.jflyfox.dudu.module.admin.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.admin.dao.ContactMapper;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.admin.service.IContactService;
import org.springframework.stereotype.Service;

/**
 * 联系人 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
@Service
public class ContactServiceImpl extends BaseServiceImpl<ContactMapper, TbContact> implements IContactService {

    public Page<TbContact> selectContactPage(Page<TbContact> page, Wrapper<TbContact> wrapper) {
        page.setRecords(baseMapper.selectContactPage(page, wrapper));
        return page;
    }
}