package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jflyfox.dudu.component.base.BaseModel;

import java.io.Serializable;

@TableName(value = "sys_dict")
public class SysDict extends BaseModel<SysDict> {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // 主键

    private String name;  // 名称

    private String type;  // 类型

    private String remark;  // 备注
    // columns END

    protected Serializable pkVal() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[name:" + getName() + "]";
        log += "[type:" + getType() + "]";
        log += "[remark:" + getRemark() + "]";
        log += super.toString();
        return log;
    }
}