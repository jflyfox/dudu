package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jflyfox.dudu.component.base.BaseModel;

import java.io.Serializable;

@TableName(value = "sys_config")
public class SysConfig extends BaseModel<SysConfig> {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // 主键

    private String name;  // 名称

    private String key;  // 键

    private String value;  // 值

    private String code;  // 编码

    private Long type;  // 类型

    private Integer sort;  // 排序号

    // columns END
    @TableField(exist = false)
    private String typeName; // 类型名称

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[name:" + getName() + "]";
        log += "[key:" + getKey() + "]";
        log += "[value:" + getValue() + "]";
        log += "[code:" + getCode() + "]";
        log += "[type:" + getType() + "]";
        log += "[sort:" + getSort() + "]";
        log += super.toString();
        return log;
    }
}