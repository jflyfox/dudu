package com.jflyfox.dudu.component.model;

import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.util.DuduConstants;
import com.jflyfox.dudu.component.util.JFlyFoxUtils;
import com.jflyfox.util.StrUtils;

import java.io.Serializable;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/24.
 */
public class JqgridBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page = 1; // 页码
    private int rows = DuduConstants.DEFAULT_PAGE_SIZE; // 行数
    private String sidx; // 排序字段
    private String sord; // 排序方式：正序、倒序

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

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Page getPagination() {
        return new Page(getPage(), getRows());
    }

    public String getOrderBy() {
        if (StrUtils.isEmpty(getSidx())) {
            return null;
        }

        return JFlyFoxUtils.camelToUnderline(getSidx()) + " " + getSord();
    }
}
