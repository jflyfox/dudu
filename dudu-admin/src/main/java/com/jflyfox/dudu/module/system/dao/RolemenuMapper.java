package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysRoleMenu;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 角色和菜单关联 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface RolemenuMapper extends BaseMapper<SysRoleMenu> {

    @SelectProvider(type = SqlBuilder.class, method = "selectRolemenuPage")
    List<SysRoleMenu> selectRolemenuPage(Query query);

    class SqlBuilder {
        public String selectRolemenuPage(Query query) {
            String sqlColumns = "t.id,t.roleid,t.menuid";
            return new SQL() {{
                SELECT(sqlColumns);
                FROM(" sys_role_menu t ");
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
