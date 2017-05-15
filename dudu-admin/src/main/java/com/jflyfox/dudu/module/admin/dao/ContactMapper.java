package com.jflyfox.dudu.module.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.module.admin.model.TbContact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 联系人 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
public interface ContactMapper extends BaseMapper<TbContact> {

    List<TbContact> selectContactPage(RowBounds rowBounds, @Param("ew") Wrapper<TbContact> wrapper);

}