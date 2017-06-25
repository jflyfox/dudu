package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jflyfox.dudu.component.base.BaseModel;

import java.io.Serializable;
import java.util.List;

@TableName(value = "sys_menu")
public class SysMenu extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // id

    private Long parentid;  // 父id

    private String name;  // 名称/11111

    private String urlkey;  // 菜单key

    private String url;  // 链接地址

    private Integer status;  // 状态//radio/2,隐藏,1,显示

    private Integer type;  // 类型//select/1,根目录,2,a标签,3,a标签_blank,4,外部url

    private Integer sort;  // 排序

    private Integer level;  // 级别

    // columns END
    @TableField(exist = false)
    private String parentName;  // 上级名称
    @TableField(exist = false)
    private List<SysMenu> childs;  // 下级菜单列表

    public Serializable pkVal() {
        return id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlkey() {
        return urlkey;
    }

    public void setUrlkey(String urlkey) {
        this.urlkey = urlkey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysMenu> getChilds() {
        return childs;
    }

    public void setChilds(List<SysMenu> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[parentid:" + getParentid() + "]";
        log += "[name:" + getName() + "]";
        log += "[urlkey:" + getUrlkey() + "]";
        log += "[url:" + getUrl() + "]";
        log += "[status:" + getStatus() + "]";
        log += "[type:" + getType() + "]";
        log += "[sort:" + getSort() + "]";
        log += "[level:" + getLevel() + "]";
        log += super.toString();
        return log;
    }
}