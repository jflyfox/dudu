package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysConfig;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 系统配置表 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface ConfigMapper extends BaseMapper<SysConfig> {

    @SelectProvider(type = SqlBuilder.class, method = "selectConfigPage")
    List<SysConfig> selectConfigPage(Query query);

    class SqlBuilder {
        public String selectConfigPage(Query query) {
            String sqlColumns = "t.id,t.name,t.key,t.value,t.code,t.type,t.sort,t.enable,t.update_time as updateTime,t.update_id as updateId,t.create_time as createTime,t.create_id as createId";
            return new SQL() {{
                SELECT(sqlColumns +
                        " ,p.name as typeName,uu.username as updateName,uc.username as createName");
                FROM(" sys_config t ");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
                LEFT_OUTER_JOIN(" sys_config p on t.type = p.id ");
                if (StrUtils.isNotEmpty(query.getStr("name"))) {
                    WHERE(" t.name like concat('%',#{name},'%')");
                }
                if (StrUtils.isNotEmpty(query.getStr("key"))) {
                    WHERE(" t.key like concat('%',#{key},'%')");
                }
                if (StrUtils.isNotEmpty(query.getStr("value"))) {
                    WHERE(" t.value like concat('%',#{value},'%')");
                }
                if (NumberUtils.parseInt(query.get("type")) > 0) {
                    WHERE(" t.type = #{type}");
                }
                if (query.get("operType")==null || query.getInt("operType")==1) {
                    WHERE(" t.type != 0 ");
                } else {
                    WHERE(" t.type = 0 ");
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
