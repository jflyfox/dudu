package com.jflyfox.dudu.module.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
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
    List<TbContact> selectContactPage(Query query);

    class SqlBuilder {
        public String selectContactPage(Query query) {
            StringBuffer sql = new StringBuffer();
            sql.append(" select" +
                    " t.id,t.name,t.phone,t.email,t.addr,t.birthday,t.remark,t.enable," +
                    " t.update_time as updateTime,t.update_id as updateId," +
                    " t.create_time as createTime,t.create_id as createId," +
                    "  uu.username as updateName,uc.username as createName" +
                    "  from tb_contact t" +
                    " left join sys_user uu on t.update_id = uu.id" +
                    " left join sys_user uc on t.create_id = uc.id");
            if (StringUtils.isNotEmpty(query.getStr("name"))) {
                sql.append(" where t.name = #{name}");
            }
            if (StrUtils.isNotEmpty(query.getOrderBy())) {
                sql.append(" order by " + query.getOrderBy());
            } else {
                sql.append(" order by t.id ");
            }
            return sql.toString();
        }
    }
}