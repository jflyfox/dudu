package com.jflyfox.dudu.module.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.dao.ContactMapper;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.admin.service.IContactService;
import com.jflyfox.util.StrUtils;
import org.springframework.stereotype.Service;

/**
 * 联系人 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
@Service
public class ContactServiceImpl extends BaseServiceImpl<ContactMapper, TbContact> implements IContactService {

    public PageInfo<TbContact> selectContactPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<TbContact> pageInfo = new PageInfo<TbContact>(baseMapper.selectContactPage(query));
        return pageInfo;
    }
}