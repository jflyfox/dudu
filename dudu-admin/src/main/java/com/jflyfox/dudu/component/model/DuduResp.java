package com.jflyfox.dudu.component.model;


import com.jflyfox.dudu.component.util.DuduConstants;
import com.jflyfox.util.DateUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * json 返回对象
 */
public class DuduResp {

    private static final String DATA = "data"; // 数据key
    private String no = DateUtils.getNow(DateUtils.YMDHMSSS); // 返回编号
    private int code = DuduConstants.CODE_SUCCESS; // 编码
    private String msg = DuduConstants.MSG_SUCCESS; // 内容
    private final Map<String, Object> attr = new ConcurrentHashMap<String, Object>(); // 返回属性

    public DuduResp() {
    }

    public DuduResp(Object data) {
        if (data != null)
            attr.put(DATA, data);
    }

    public String getNo() {
        return no;
    }

    public DuduResp setNo(String no) {
        this.no = no;
        return this;
    }

    public boolean success() {
        return code == DuduConstants.CODE_SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public DuduResp setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public DuduResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return this.attr.get(DATA);
    }

    public DuduResp setData(Object data) {
        if (data != null)
            this.attr.put(DATA, data);
        return this;
    }

    public Map<String, Object> getAttr() {
        return this.attr;
    }

    public DuduResp addAttr(String key, Object value) {
        if (value != null)
            this.attr.put(key, value);
        return this;
    }

    public Object getAttr(String key) {
        return this.attr.get(key);
    }

    @Override
    public String toString() {
        return "[no=" + this.no + "]" //
                + "[code=" + this.code + "]" //
                + "[msg=" + this.msg + "]" //
                + "[data=" + this.attr.get(DATA) + "]" //
                + "[attr=" + this.attr + "]";
    }

}
