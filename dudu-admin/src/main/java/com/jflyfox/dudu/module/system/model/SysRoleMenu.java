package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_role_menu")
public class SysRoleMenu extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // id

    private Long roleid;  // 角色id

    private Long menuid;  // 菜单id
    // columns END

    public Serializable pkVal() {
        return id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[roleid:" + getRoleid() + "]";
        log += "[menuid:" + getMenuid() + "]";
        log += super.toString();
        return log;
    }
}