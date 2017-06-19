package com.jflyfox.dudu.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 用户 数据层
 *
 * @author flyfox 369191470@qq.com on 2017-06-20.
 */
public interface UserMapper extends BaseMapper<SysUser> {

    @SelectProvider(type = SqlBuilder.class, method = "selectUserPage")
    List<SysUser> selectUserPage(Query query);

    class SqlBuilder {
        public String selectUserPage(Query query) {
            String sqlColumns = "t.id,t.uuid,t.username,t.password,t.realname,t.departid,t.usertype,t.status,t.thirdid,t.endtime,t.email,t.tel,t.address,t.title_url as titleUrl,t.remark,t.theme,t.back_site_id as backSiteId,t.create_site_id as createSiteId,t.enable,t.update_time as updateTime,t.update_id as updateId,t.create_time as createTime,t.create_id as createId";
            return new SQL() {{
                SELECT(sqlColumns +
                        " ,d.name as departName,uu.username as updateName,uc.username as createName");
                FROM(" sys_user t ");
                LEFT_OUTER_JOIN(" sys_user uu on t.update_id = uu.id ");
                LEFT_OUTER_JOIN(" sys_user uc on t.create_id = uc.id ");
                LEFT_OUTER_JOIN(" sys_department d on t.departid = d.id ");

                if (StrUtils.isNotEmpty(query.getStr("username"))) {
                    WHERE(" t.username like concat('%',#{username},'%')");
                }
                if (StrUtils.isNotEmpty(query.getStr("realname"))) {
                    WHERE(" t.realname like concat('%',#{realname},'%')");
                }
                if (NumberUtils.parseInt(query.get("usertype")) > 0) {
                    WHERE(" t.usertype = #{usertype}");
                }
                if (NumberUtils.parseInt(query.get("departid")) > 0) {
                    WHERE(" t.departid = #{departid}");
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
