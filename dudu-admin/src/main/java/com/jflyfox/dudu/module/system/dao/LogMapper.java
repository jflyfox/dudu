package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysLog;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 日志 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface LogMapper extends BaseMapper<SysLog> {

    @SelectProvider(type = SqlBuilder.class, method = "selectLogPage")
    List<SysLog> selectLogPage(Query query);

    class SqlBuilder {
        public String selectLogPage(Query query) {
            String sqlColumns = "t.id,t.log_type as logType,t.oper_object as operObject,t.oper_table as operTable,t.oper_id as operId,t.oper_type as operType,t.oper_remark as operRemark,t.enable,t.update_time as updateTime,t.update_id as updateId,t.create_time as createTime,t.create_id as createId";
            return new SQL() {{
                SELECT(sqlColumns +
                        " ,uu.username as updateName,uc.username as createName");
                FROM(" sys_log t ");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
                if (StrUtils.isNotEmpty(query.getStr("operObject"))) {
                    WHERE(" t.oper_object = #{operObject}");
                }
                if (StrUtils.isNotEmpty(query.getStr("operTable"))) {
                    WHERE(" t.oper_table like concat('%',#{operTable},'%')");
                }
                if (NumberUtils.parseInt(query.get("logType")) > 0 ) {
                    WHERE(" t.log_type like concat('%',#{logType},'%')");
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
