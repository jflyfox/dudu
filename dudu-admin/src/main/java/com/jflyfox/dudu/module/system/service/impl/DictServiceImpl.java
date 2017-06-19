package com.jflyfox.dudu.module.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysDict;
import com.jflyfox.dudu.module.system.dao.DictMapper;
import com.jflyfox.dudu.module.system.service.IDictService;

/**
 * 数据字典主表 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, SysDict> implements IDictService {

    public PageInfo<SysDict> selectDictPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysDict> pageInfo = new PageInfo<SysDict>(baseMapper.selectDictPage(query));
        return pageInfo;
    }
}