package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.system.dao.DictdetailMapper;
import com.jflyfox.dudu.module.system.model.SysDictDetail;
import com.jflyfox.dudu.module.system.service.IDictService;
import com.jflyfox.dudu.module.system.service.IDictdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据字典 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class DictdetailServiceImpl extends BaseServiceImpl<DictdetailMapper, SysDictDetail> implements IDictdetailService {

    @Autowired
    private IDictService dictService;

    public Page<SysDictDetail> selectDictdetailPage(Page<SysDictDetail> page, Wrapper<SysDictDetail> wrapper) {
        page.setRecords(baseMapper.selectDictdetailPage(page, wrapper));
        return page;
    }

}