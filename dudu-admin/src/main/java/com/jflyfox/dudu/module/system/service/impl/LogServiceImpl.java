package com.jflyfox.dudu.module.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.model.Query;
import org.springframework.stereotype.Service;
import com.jflyfox.dudu.module.system.model.SysLog;
import com.jflyfox.dudu.module.system.dao.LogMapper;
import com.jflyfox.dudu.module.system.service.ILogService;

/**
 * 日志 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogMapper, SysLog> implements ILogService {

    public PageInfo<SysLog> selectLogPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(baseMapper.selectLogPage(query));
        return pageInfo;
    }
}