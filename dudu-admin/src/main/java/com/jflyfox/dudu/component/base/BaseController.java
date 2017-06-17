package com.jflyfox.dudu.component.base;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.component.util.DuduRespUtils;
import com.jflyfox.dudu.component.util.StringEscapeEditor;
import com.jflyfox.dudu.module.system.service.ISessionAttrService;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by flyfox 369191470@qq.com on 2017/4/20.
 */
public class BaseController extends BaseSupportController {

    protected static final String pageMessage = "/pages/template/message.html";

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
    }

    protected void renderMessage(String message) {
        renderMessage(message, "closeIframe();");
    }

    protected void renderMessageByFailed(String message) {
        renderMessage(message, "history.back();");
    }

    @Autowired
    ISessionAttrService sessionAttrService;

    protected void renderMessage(String message, String obj) {
        String script = "";
        if (StrUtils.isEmpty(obj)) { // 关闭页面
            script = "closeIframe();";
        } else if (script.endsWith(".jsp")) { // 页面跳转
            script = "window.location.href = \"" + obj + "\"";
        } else { // 直接执行JS
            script = obj;
        }
        request.setAttribute("msg", message);
        request.setAttribute("script", script);
        try {
            request.getRequestDispatcher(pageMessage).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证Session User是否合法
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public boolean validSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        return (subject.isAuthenticated() || subject.isRemembered());
    }

    /**
     * 获取Session User
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public SessionUser getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
        // 没有登录
        if (!(subject.isAuthenticated() || subject.isRemembered())) {
            return new SessionUser();
        }

        SessionUser sessionUser = (SessionUser) subject.getPrincipal();
        return sessionUser;
    }

    /**
     * 获取当前时间，保存创建时间使用
     * <p>
     * 2015年3月25日 下午3:48:02 flyfox 330627517@qq.com
     *
     * @return
     */
    protected String getNow() {
        return DateUtils.getNow(DateUtils.YMD_HMS);
    }

    protected JSONObject success() {
        return DuduRespUtils.success(null);
    }

    protected JSONObject success(Object data) {
        return DuduRespUtils.success(data);
    }

    protected JSONObject fail(Object data, String msg) {
        return DuduRespUtils.fail(null, msg);
    }

    protected JSONObject fail(String msg) {
        return DuduRespUtils.fail(null, msg);
    }

    protected JSONObject getJqgridList(Page pageData) {
        JSONObject json = new JSONObject();
        json.put("page", pageData.getCurrent());
        json.put("total", pageData.getPages());
        json.put("records", pageData.getTotal());
        json.put("rows", pageData.getRecords());
        return json;
    }

    protected JSONObject getJqgridList(PageInfo pageData) {
        JSONObject json = new JSONObject();
        json.put("page", pageData.getPageNum());
        json.put("total", pageData.getPages());
        json.put("records", pageData.getTotal());
        json.put("rows", pageData.getList());
        return json;
    }
}
