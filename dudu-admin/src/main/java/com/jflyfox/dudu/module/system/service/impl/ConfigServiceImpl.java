package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.common.ConfigConstants;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.system.dao.ConfigMapper;
import com.jflyfox.dudu.module.system.model.SysConfig;
import com.jflyfox.dudu.module.system.service.IConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 系统配置表 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, SysConfig> implements IConfigService {

    public static final String CACHENAMES_CONFIG = "configserviceimpl";
    public static final String CACHE_CONFIG_KEY = "'config'";

    public PageInfo<SysConfig> selectConfigPage(Query query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysConfig> pageInfo = new PageInfo<SysConfig>(baseMapper.selectConfigPage(query));
        return pageInfo;
    }

    @Override
    public boolean debug() {
        return "true".equals(getValue(ConfigConstants.SYSTEM_DEBUG));
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

    @CacheEvict(cacheNames = CACHENAMES_CONFIG, key = CACHE_CONFIG_KEY)
    @Override
    public boolean insertLog(SysConfig entity) {
        return super.insertLog(entity);
    }

    @CacheEvict(cacheNames = CACHENAMES_CONFIG, key = CACHE_CONFIG_KEY)
    @Override
    public boolean updateByIdLog(SysConfig entity) {
        return super.updateByIdLog(entity);
    }

    @CacheEvict(cacheNames = CACHENAMES_CONFIG, key = CACHE_CONFIG_KEY)
    @Override
    public boolean deleteByIdLog(SysConfig entity) {
        return super.deleteByIdLog(entity);
    }

    public String getValue(String key) {
        Optional<SysConfig> optional = getList().stream().filter(config -> config.getKey().equals(key)).findFirst();
        return optional.map(u -> u.getValue()).orElse(null);
    }

    public String getCode(String key) {
        Optional<SysConfig> optional = getList().stream().filter(config -> config.getKey().equals(key)).findFirst();
        return optional.map(u -> u.getCode()).orElse(null);
    }

    @Cacheable(cacheNames = CACHENAMES_CONFIG, key = CACHE_CONFIG_KEY)
    public List<SysConfig> getList() {
        Wrapper<SysConfig> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort,create_time desc");
        return selectList(wrapper);
    }
}