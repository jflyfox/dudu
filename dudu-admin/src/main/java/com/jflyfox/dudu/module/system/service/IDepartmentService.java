package com.jflyfox.dudu.module.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.IBaseService;
import com.jflyfox.dudu.module.system.model.SysDepartment;

/**
 * 部门 服务接口层
 *
 * @author flyfox 369191470@qq.com on 2017-04-23.
 */
public interface IDepartmentService extends IBaseService<SysDepartment> {


    /**
     * 分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    Page<SysDepartment> selectDepartmentPage(Page<SysDepartment> page, Wrapper<SysDepartment> wrapper);

    /**
     * 获取部门下拉框
     * <p>
     * 2015年4月28日 上午11:42:54 flyfox 369191470@qq.com
     *
     * @param selected
     * @return
     */
    String selectDepart(long selected);

    /**
     * 获取部门下拉框
     * <p>
     * 2015年1月28日 下午5:28:40 flyfox 369191470@qq.com
     *
     * @return
     */
    String selectDepart(long selected, long selfId);

}