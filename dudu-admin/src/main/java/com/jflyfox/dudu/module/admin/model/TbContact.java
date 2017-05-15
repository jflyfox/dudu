package com.jflyfox.dudu.module.admin.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jflyfox.dudu.component.base.BaseModel;

import java.io.Serializable;

@TableName(value = "tb_contact")
public class TbContact extends BaseModel<TbContact> {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // 主键

    private String name;  // 姓名

    private String phone;  // 手机号

    private String email;  // Email

    private String addr;  // 地址

    private String birthday;  // 生日

    private String remark;  // 说明
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
        log += "[phone:" + getPhone() + "]";
        log += "[email:" + getEmail() + "]";
        log += "[addr:" + getAddr() + "]";
        log += "[birthday:" + getBirthday() + "]";
        log += "[remark:" + getRemark() + "]";
        log += super.toString();
        return log;
    }
}