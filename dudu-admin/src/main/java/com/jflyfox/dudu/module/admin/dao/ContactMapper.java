package com.jflyfox.dudu.module.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

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
            return new SQL() {{
                SELECT(" t.id,t.name,t.phone,t.email,t.addr,t.birthday,t.remark,t.enable," +
                        " t.update_time as updateTime,t.update_id as updateId," +
                        " t.create_time as createTime,t.create_id as createId," +
                        "  uu.username as updateName,uc.username as createName");
                FROM(" tb_contact t ");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
                if (StrUtils.isNotEmpty(query.getStr("name"))) {
                    WHERE(" t.name = #{name}");
                }
                if (StrUtils.isNotEmpty(query.getOrderBy())) {
                    ORDER_BY(query.getOrderBy());
                } else {
                    ORDER_BY(" t.id ");
                }
            }}.toString();
        }
    }
}