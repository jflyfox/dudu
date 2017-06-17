package com.jflyfox.dudu.module.system.model;

import com.jflyfox.dudu.component.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_dict_detail")
public class SysDictDetail extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

    // columns START

	@TableId(value = "id")
	private Long id;  // 主键

	@TableField(value = "dict_type")
	private String dictType;  // 数据字典类型

	private String name;  // 名称

	private String code;  // 代码

	private String sort;  // 排序号

	private String type;  // 类型

	private String status;  // 状态

	private String content;  // 内容

	private String remark;  // 备注
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

	public String getDictType() {
		return dictType;
	}

    public void setDictType(String dictType) {
    	this.dictType = dictType;
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

	public String getSort() {
		return sort;
	}

    public void setSort(String sort) {
    	this.sort = sort;
    }

	public String getType() {
		return type;
	}

    public void setType(String type) {
    	this.type = type;
    }

	public String getStatus() {
		return status;
	}

    public void setStatus(String status) {
    	this.status = status;
    }

	public String getContent() {
		return content;
	}

    public void setContent(String content) {
    	this.content = content;
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
		log += "[dictType:" + getDictType() + "]";
		log += "[name:" + getName() + "]";
		log += "[code:" + getCode() + "]";
		log += "[sort:" + getSort() + "]";
		log += "[type:" + getType() + "]";
		log += "[status:" + getStatus() + "]";
		log += "[content:" + getContent() + "]";
		log += "[remark:" + getRemark() + "]";
		log += super.toString();
		return log;
	}
}