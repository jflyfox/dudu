package com.jflyfox.dudu.component.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer enable;  // 是否启用
    @TableField(value = "update_time")
    private String updateTime;  // 更新时间
    @TableField(value = "update_id")
    private Long updateId;  // 更新人
    @TableField(value = "create_time")
    private String createTime;  // 创建时间
    @TableField(value = "create_id")
    private Long createId;  // 创建者
    // 非数据库字段
    @TableField(exist = false)
    private String updateName;  // 更新人
    @TableField(exist = false)
    private String createName;  // 创建人

    public abstract Serializable pkVal();

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[enable:" + getEnable() + "]";
        log += "[updateTime:" + getUpdateTime() + "]";
        log += "[updateId:" + getUpdateId() + "]";
        log += "[createTime:" + getCreateTime() + "]";
        log += "[createId:" + getCreateId() + "]";
        return log;
    }
}
