package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysDictDetail;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 数据字典 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface DictdetailMapper extends BaseMapper<SysDictDetail> {

    @SelectProvider(type = SqlBuilder.class, method = "selectDictdetailPage")
    List<SysDictDetail> selectDictdetailPage(Query query);

    class SqlBuilder {
        public String selectDictdetailPage(Query query) {
            String sqlColumns = "t.id,t.dict_type as dictType,t.name,t.code,t.sort,t.type,t.status,t.content,t.remark,t.enable,t.update_time as updateTime,t.update_id as updateId,t.create_time as createTime,t.create_id as createId";
            return new SQL() {{
                SELECT(sqlColumns +
                        " ,uu.username as updateName,uc.username as createName");
                FROM(" sys_dict_detail t ");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
                if (StrUtils.isNotEmpty(query.getStr("name"))) {
                    WHERE(" t.name like concat('%',#{name},'%')");
                }
                if (StrUtils.isNotEmpty(query.getStr("dictType")) && "-1".equals(query.getStr("dictType"))) {
                    WHERE(" t.dict_type = #{dictType}");
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
