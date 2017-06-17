package com.jflyfox.dudu.module.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.model.TbContact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 联系人 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
public interface ContactMapper extends BaseMapper<TbContact> {

    @SelectProvider(type = SqlBuilder.class, method = "selectContactPage")
    List<TbContact> selectContactPage(Map<String, Object> query);

    class SqlBuilder {
        public String selectContactPage(Map<String, Object> query) {
            String sql = " select" +
                    " t.id,t.name,t.phone,t.email,t.addr,t.birthday,t.remark,t.enable," +
                    " t.update_time as updateTime,t.update_id as updateId," +
                    " t.create_time as createTime,t.create_id as createId," +
                    "  uu.username as updateName,uc.username as createName" +
                    "  from tb_contact t" +
                    " left join sys_user uu on t.update_id = uu.id" +
                    " left join sys_user uc on t.create_id = uc.id" +
                    query.get("orderBy");
                    // "${orderBy}";
                    // " #{ew.sqlSegment}";
            return sql;
        }
    }
}