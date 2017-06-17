package com.jflyfox.dudu.module.system.model;

import com.jflyfox.dudu.component.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_role")
public class SysRole extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

    // columns START

	@TableId(value = "id")
	private Long id;  // id

	private String name;  // 名称/11111/

	private Integer status;  // 状态//radio/2,隐藏,1,显示

	private Integer sort;  // 排序

	private String remark;  // 说明//textarea
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

	public String getName() {
		return name;
	}

    public void setName(String name) {
    	this.name = name;
    }

	public Integer getStatus() {
		return status;
	}

    public void setStatus(Integer status) {
    	this.status = status;
    }

	public Integer getSort() {
		return sort;
	}

    public void setSort(Integer sort) {
    	this.sort = sort;
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
		log += "[status:" + getStatus() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[remark:" + getRemark() + "]";
		log += super.toString();
		return log;
	}
}