package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 用户和角色关联 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface UserroleMapper extends BaseMapper<SysUserRole> {

    List<SysUserRole> selectUserrolePage(RowBounds rowBounds, @Param("ew") Wrapper<SysUserRole> wrapper);

}