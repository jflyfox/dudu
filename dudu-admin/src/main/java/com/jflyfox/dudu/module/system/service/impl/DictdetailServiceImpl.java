package com.jflyfox.dudu.module.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysDictDetail;
import com.jflyfox.dudu.module.system.dao.DictdetailMapper;
import com.jflyfox.dudu.module.system.service.IDictdetailService;

/**
 * 数据字典 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
@Service
public class DictdetailServiceImpl extends BaseServiceImpl<DictdetailMapper, SysDictDetail> implements IDictdetailService {

    public PageInfo<SysDictDetail> selectDictdetailPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysDictDetail> pageInfo = new PageInfo<SysDictDetail>(baseMapper.selectDictdetailPage(query));
        return pageInfo;
    }
}