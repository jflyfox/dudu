package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysDepartment;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 部门 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-04-23.
 */
public interface DepartmentMapper extends BaseMapper<SysDepartment> {


    @Select("SELECT * FROM sys_department WHERE 1=1")
    List<SysDepartment> selectAll();

    @SelectProvider(type = SqlBuilder.class, method = "selectDepartmentPage")
    List<SysDepartment> selectDepartmentPage(Query query);

    class SqlBuilder {
        public String selectDepartmentPage(Query query) {
            String sqlColumns = "t.id,t.parentid,t.name,t.code,t.sort,t.linkman,t.linkman_no as linkmanNo,t.remark,t.enable,t.update_time as updateTime,t.update_id as updateId,t.create_time as createTime,t.create_id as createId";
            return new SQL() {{
                SELECT(sqlColumns +
                        " ,p.name as parentName,uu.username as updateName,uc.username as createName");
                FROM(" sys_department t ");
                LEFT_OUTER_JOIN(" sys_department p on t.parentid = p.id");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
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