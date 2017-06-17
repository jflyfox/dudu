package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jflyfox.dudu.component.base.BaseModel;

import java.io.Serializable;

@TableName(value = "sys_department")
public class SysDepartment extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // columns START
    @TableId(value = "id")
    private Long id;  //
    private Integer parentid;  // 上级机构
    private String name;  // 部门/11111
    private String code;  // 机构编码
    private Integer sort;  // 序号
    private String linkman;  // 联系人
    @TableField(value = "linkman_no")
    private String linkmanNo;  // 联系人电话
    private String remark;  // 机构描述
    // columns END
    @TableField(exist = false)
    private String parentName;  // 上级机构名称

    public Serializable pkVal() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanNo() {
        return linkmanNo;
    }

    public void setLinkmanNo(String linkmanNo) {
        this.linkmanNo = linkmanNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[parentid:" + getParentid() + "]";
        log += "[name:" + getName() + "]";
        log += "[code:" + getCode() + "]";
        log += "[sort:" + getSort() + "]";
        log += "[linkman:" + getLinkman() + "]";
        log += "[linkmanNo:" + getLinkmanNo() + "]";
        log += "[remark:" + getRemark() + "]";
        log += super.toString();
        return log;
    }
}