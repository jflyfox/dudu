package com.jflyfox.dudu.component.util;

import com.alibaba.fastjson.JSONObject;
import com.jflyfox.dudu.component.model.DuduResp;

import java.util.Map;

/**
 * resp 工具类
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/24.
 */
public class DuduRespUtils {

    /**
     * resp转json
     *
     * @param resp
     * @return
     */
    public static JSONObject json(DuduResp resp) {
        JSONObject json = new JSONObject();
        json.put("no", resp.getNo());
        json.put("code", resp.getCode());
        json.put("msg", resp.getMsg());
        for (Map.Entry<String, Object> entry : resp.getAttr().entrySet()) {
            if (entry.getValue() != null)
                json.put(entry.getKey(), entry.getValue());
        }
        return json;
    }

    /**
     * 成功RESP
     *
     * @param data
     * @return
     */
    public static JSONObject success(Object data) {
        DuduResp resp = new DuduResp(data);
        return json(resp);
    }

    /**
     * 失败resp
     *
     * @param data
     * @param msg
     * @return
     */
    public static JSONObject fail(Object data, String msg) {
        DuduResp resp = new DuduResp(data);
        resp.setCode(DuduConstants.CODE_FAIL).setMsg(msg);
        return json(resp);
    }

    /**
     * 异常resp
     *
     * @param data
     * @param msg
     * @return
     */
    public static JSONObject error(Object data, String msg) {
        DuduResp resp = new DuduResp(data);
        resp.setCode(DuduConstants.CODE_ERROR).setMsg(msg);
        return json(resp);
    }


}
