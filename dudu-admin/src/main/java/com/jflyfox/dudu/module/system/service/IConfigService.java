package com.jflyfox.dudu.module.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.module.system.model.SysConfig;

import java.util.List;

/**
 * 系统配置表 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IConfigService extends IBaseService<SysConfig> {

    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysConfig> selectConfigPage(Page<SysConfig> page, Wrapper<SysConfig> wrapper);

    /**
     * 类型列表
     *
     * @param selected
     * @return
     */
    String selectType(Long selected);

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 获取编码
     *
     * @param key
     * @return
     */
    String getCode(String key);

    /**
     * 获取列表
     *
     * @return
     */
    List<SysConfig> getList();

    /**
     * 是否开启debug
     *
     * @return
     */
    boolean debug();
}