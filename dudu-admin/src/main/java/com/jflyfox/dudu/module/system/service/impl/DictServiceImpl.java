package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysDict;
import com.jflyfox.dudu.module.system.dao.DictMapper;
import com.jflyfox.dudu.module.system.service.IDictService;

/**
 * 数据字典主表 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictMapper, SysDict> implements IDictService {

    public Page<SysDict> selectDictPage(Page<SysDict> page, Wrapper<SysDict> wrapper) {
        page.setRecords(baseMapper.selectDictPage(page, wrapper));
        return page;
    }
}