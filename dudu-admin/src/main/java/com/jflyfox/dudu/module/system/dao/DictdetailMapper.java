package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.module.system.model.SysDictDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 数据字典 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
public interface DictdetailMapper extends BaseMapper<SysDictDetail> {

    List<SysDictDetail> selectDictdetailPage(RowBounds rowBounds, @Param("ew") Wrapper<SysDictDetail> wrapper);

}