package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.system.dao.ConfigMapper;
import com.jflyfox.dudu.module.system.model.SysConfig;
import com.jflyfox.dudu.module.system.service.IConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统配置表 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, SysConfig> implements IConfigService {

    public Page<SysConfig> selectConfigPage(Page<SysConfig> page, Wrapper<SysConfig> wrapper) {
        page.setRecords(baseMapper.selectConfigPage(page, wrapper));
        return page;
    }

    public String selectType(Long selected) {
        Wrapper<SysConfig> wrapper = new EntityWrapper<>();
        wrapper.eq("type", 0).orderBy("sort,create_time desc");
        List<SysConfig> list = selectList(wrapper);


        StringBuffer sb = new StringBuffer();
        list.forEach(config -> {
            sb.append("<option value=\"");
            sb.append(config.getId());
            sb.append("\" ");
            sb.append((selected != null && config.getId() == selected) ? "selected" : "");
            sb.append(">");
            sb.append(config.getName());
            sb.append("</option>");
        });
        return sb.toString();
    }
}