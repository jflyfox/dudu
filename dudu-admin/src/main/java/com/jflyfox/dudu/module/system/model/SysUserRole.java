package com.jflyfox.dudu.module.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // id

    private Long userid;  // 用户id

    private Long roleid;  // 角色id
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[userid:" + getUserid() + "]";
        log += "[roleid:" + getRoleid() + "]";
        log += super.toString();
        return log;
    }
}