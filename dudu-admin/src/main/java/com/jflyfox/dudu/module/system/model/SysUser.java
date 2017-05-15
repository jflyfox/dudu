package com.jflyfox.dudu.module.system.model;

import com.jflyfox.dudu.component.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_user")
public class SysUser extends BaseModel<SysUser> {

    private static final long serialVersionUID = 1L;

    // columns START

    @TableId(value = "id")
    private Long id;  // 主键

    private String uuid;  // UUID

    private String username;  // 用户名/11111

    private String password;  // 密码

    private String realname;  // 真实姓名

    private Integer departid;  // 部门/11111/dict

    private Integer usertype;  // 类型//select/1,管理员,2,普通用户,3,前台用户,4,第三方用户,5,API用户

    private Integer status;  // 状态

    private String thirdid;  // 第三方ID

    private String endtime;  // 结束时间

    private String email;  // email

    private String tel;  // 手机号

    private String address;  // 地址

    @TableField(value = "title_url")
    private String titleUrl;  // 头像地址

    private String remark;  // 说明

    private String theme;  // 主题

    @TableField(value = "back_site_id")
    private Integer backSiteId;  // 后台选择站点ID

    @TableField(value = "create_site_id")
    private Integer createSiteId;  // 创建站点ID

    // columns END
    @TableField(exist = false)
    private String departName;

    protected Serializable pkVal() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThirdid() {
        return thirdid;
    }

    public void setThirdid(String thirdid) {
        this.thirdid = thirdid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getBackSiteId() {
        return backSiteId;
    }

    public void setBackSiteId(Integer backSiteId) {
        this.backSiteId = backSiteId;
    }

    public Integer getCreateSiteId() {
        return createSiteId;
    }

    public void setCreateSiteId(Integer createSiteId) {
        this.createSiteId = createSiteId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Override
    public String toString() {
        String log = "";
        log += "[id:" + getId() + "]";
        log += "[uuid:" + getUuid() + "]";
        log += "[username:" + getUsername() + "]";
        log += "[password:" + getPassword() + "]";
        log += "[realname:" + getRealname() + "]";
        log += "[departid:" + getDepartid() + "]";
        log += "[usertype:" + getUsertype() + "]";
        log += "[status:" + getStatus() + "]";
        log += "[thirdid:" + getThirdid() + "]";
        log += "[endtime:" + getEndtime() + "]";
        log += "[email:" + getEmail() + "]";
        log += "[tel:" + getTel() + "]";
        log += "[address:" + getAddress() + "]";
        log += "[titleUrl:" + getTitleUrl() + "]";
        log += "[remark:" + getRemark() + "]";
        log += "[theme:" + getTheme() + "]";
        log += "[backSiteId:" + getBackSiteId() + "]";
        log += "[createSiteId:" + getCreateSiteId() + "]";
        log += super.toString();
        return log;
    }
}