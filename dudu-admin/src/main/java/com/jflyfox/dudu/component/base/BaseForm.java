/**
 * Copyright 2015-2025 FLY的狐狸(email:jflyfox@sina.com qq:369191470).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jflyfox.dudu.component.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.util.StrUtils;

import java.util.List;

/**
 * baseForm 表单参数
 *
 * @author flyfox
 * 2014-2-11
 */
public class BaseForm {

    private Page page;
    private String orderColumn;
    private String orderAsc;
    private boolean showCondition;

    /**
     * 设置Where条件
     *
     * @author flyfox 2013-12-2
     */
    public void setWhere() {
    }

    /**
     * 设置Where条件
     *
     * @param where
     * @author flyfox 2013-12-2
     */
    public void setWhere(List<Object> where) {
    }

    public boolean isShowCondition() {
        return showCondition;
    }

    public void setShowCondition(boolean showCondition) {
        this.showCondition = showCondition;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getOrderBy() {
        if (StrUtils.isEmpty(getOrderColumn())) {
            return "";
        }
        return " " + getOrderColumn() + " " + getOrderAsc() + " ";
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(String orderAsc) {
        this.orderAsc = orderAsc;
    }

}
