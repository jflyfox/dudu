package com.jflyfox.dudu.module.system.model;

import com.jflyfox.dudu.component.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName(value = "sys_log")
public class SysLog extends BaseModel<SysLog> {

	private static final long serialVersionUID = 1L;

    // columns START

	@TableId(value = "id")
	private Long id;  // 

	@TableField(value = "log_type")
	private Integer logType;  // 类型

	@TableField(value = "oper_object")
	private String operObject;  // 操作对象

	@TableField(value = "oper_table")
	private String operTable;  // 操作表

	@TableField(value = "oper_id")
	private Long operId;  // 操作主键

	@TableField(value = "oper_type")
	private String operType;  // 操作类型

	@TableField(value = "oper_remark")
	private String operRemark;  // 操作备注
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

	public Integer getLogType() {
		return logType;
	}

    public void setLogType(Integer logType) {
    	this.logType = logType;
    }

	public String getOperObject() {
		return operObject;
	}

    public void setOperObject(String operObject) {
    	this.operObject = operObject;
    }

	public String getOperTable() {
		return operTable;
	}

    public void setOperTable(String operTable) {
    	this.operTable = operTable;
    }

	public Long getOperId() {
		return operId;
	}

    public void setOperId(Long operId) {
    	this.operId = operId;
    }

	public String getOperType() {
		return operType;
	}

    public void setOperType(String operType) {
    	this.operType = operType;
    }

	public String getOperRemark() {
		return operRemark;
	}

    public void setOperRemark(String operRemark) {
    	this.operRemark = operRemark;
    }
	
	@Override
	public String toString() {
		String log = ""; 
		log += "[id:" + getId() + "]";
		log += "[logType:" + getLogType() + "]";
		log += "[operObject:" + getOperObject() + "]";
		log += "[operTable:" + getOperTable() + "]";
		log += "[operId:" + getOperId() + "]";
		log += "[operType:" + getOperType() + "]";
		log += "[operRemark:" + getOperRemark() + "]";
		log += super.toString();
		return log;
	}
}