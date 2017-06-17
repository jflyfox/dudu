package com.jflyfox.dudu.component.model;


import com.jflyfox.dudu.component.util.xss.SQLFilter;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-14 23:15
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page;
    //每页条数
    private int rows;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = NumberUtils.parseInt(params.get("page"));
        this.rows = NumberUtils.parseInt(params.get("rows"));
        this.put("page", page);
        this.put("rows", rows);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = params.get("sidx") == null ? "" : params.get("sidx").toString();
        String sord = params.get("sord") == null ? "" : params.get("sord").toString();
        this.put("sidx", SQLFilter.sqlInject(sidx));
        this.put("sord", SQLFilter.sqlInject(sord));
        String orderBy = "";
        if (StrUtils.isNotEmpty(sidx)) {
            orderBy += " order by " + SQLFilter.sqlInject(sidx);
        }
        if (StrUtils.isNotEmpty(sord)) {
            orderBy += " " + SQLFilter.sqlInject(sord);
        }
        this.put("orderBy", orderBy);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
