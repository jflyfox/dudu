package com.jflyfox.dudu.module.system.service;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysMenu;

import java.util.List;

/**
 * 菜单 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface IMenuService extends IBaseService<SysMenu> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageInfo<SysMenu> selectMenuPage(Query query);

    /**
     * 获取菜单下拉框
     * <p>
     * 2015年4月28日 上午11:42:54 flyfox 369191470@qq.com
     *
     * @param selected
     * @return
     */
    String selectMenu(long selected);

    /**
     * 获取菜单下拉框
     * <p>
     * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
     *
     * @return
     */
    String selectMenu(long selected, long selfId);

    /**
     * 获取当前用户菜单列表
     *
     * @param query
     * @return
     */
    List<SysMenu> listUserMenu(Query query);

}