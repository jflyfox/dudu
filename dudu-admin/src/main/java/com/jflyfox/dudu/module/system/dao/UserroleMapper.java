package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysUserRole;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 用户和角色关联 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface UserroleMapper extends BaseMapper<SysUserRole> {

    @SelectProvider(type = SqlBuilder.class, method = "selectUserrolePage")
    List<SysUserRole> selectUserrolePage(Query query);

    class SqlBuilder {
        public String selectUserrolePage(Query query) {
            String sqlColumns = "t.id,t.userid,t.roleid";
            return new SQL() {{
                SELECT(sqlColumns);
                FROM(" sys_user_role t ");
                if (StrUtils.isNotEmpty(query.getStr("name"))) {
                    WHERE(" t.name like concat('%',#{name},'%')");
                }
                if (StrUtils.isNotEmpty(query.getOrderBy())) {
                    ORDER_BY(query.getOrderBy());
                } else {
                    ORDER_BY(" t.id desc");
                }
            }}.toString();
        }
    }
}
