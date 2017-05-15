package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.module.system.model.SysDepartment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 部门 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-04-23.
 */
public interface DepartmentMapper extends BaseMapper<SysDepartment> {

    List<SysDepartment> selectDepartmentPage(RowBounds rowBounds, @Param("ew") Wrapper<SysDepartment> wrapper);

    @Select("SELECT * FROM sys_department WHERE 1=1")
    List<SysDepartment> selectAll();
}