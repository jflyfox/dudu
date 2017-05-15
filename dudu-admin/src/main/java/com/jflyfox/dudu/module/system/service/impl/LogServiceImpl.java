package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.system.dao.LogMapper;
import com.jflyfox.dudu.module.system.model.SysLog;
import com.jflyfox.dudu.module.system.service.ILogService;
import org.springframework.stereotype.Service;

/**
 * 日志 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<LogMapper, SysLog> implements ILogService {

    public Page<SysLog> selectLogPage(Page<SysLog> page, Wrapper<SysLog> wrapper) {
        page.setRecords(baseMapper.selectLogPage(page, wrapper));
        return page;
    }

}