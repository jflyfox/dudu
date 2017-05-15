package com.jflyfox.dudu.component.base;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.common.Constants;
import com.jflyfox.dudu.component.model.SessionUser;
import com.jflyfox.dudu.component.util.DuduRespUtils;
import com.jflyfox.dudu.component.util.EncryptUtils;
import com.jflyfox.dudu.component.util.StringEscapeEditor;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.service.ISessionAttrService;
import com.jflyfox.util.DateUtils;
import com.jflyfox.util.NumberUtils;
import com.jflyfox.util.StrUtils;
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
        SessionUser sessionUser = getSessionUser();
        return !(sessionUser == null || sessionUser.getId() <= 0);
    }

    /**
     * 获取Session User
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public SessionUser getSessionUser() {
        SessionUser sessionUser = getSessionAttr(Constants.SESSION_USER);
        try {
            // 如果session没有，cookie有~那么就设置到Session
            if (sessionUser == null) {
                String cookieContent = getCookie(Constants.SESSION_USER);
                if (cookieContent != null) {
                    String key = EncryptUtils.dataDecrypt(cookieContent);
                    if (StrUtils.isNotEmpty(key) && key.split(",").length == 2) {
                        int userid = NumberUtils.parseInt(key.split(",")[0]);
                        String time = key.split(",")[1]; // 如果需要可以做超时处理
                        // 查询用户信息
                        SysUser sysUser = sessionAttrService.getSysUser(userid);
                        if (sysUser != null) {
                            sessionUser = sessionAttrService.newSessionUser(sysUser);
                            setSessionUser(sessionUser);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // 异常cookie重新登陆
            removeSessionAttr(Constants.SESSION_USER);
            removeCookie(Constants.SESSION_USER);

            logger.error("cooke user异常:", e);
            return null;
        }
        return sessionUser;
    }

    /**
     * 方法重写
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public SessionUser setSessionUser(SessionUser user) {
        setSessionAttr(Constants.SESSION_USER, user);
        // 设置cookie，用id+password作为
        String key = user.getId() + "," + DateUtils.getNow(DateUtils.YMDHMS);
        String cookieContent = EncryptUtils.dataEncrypt(key);
        setCookie(Constants.SESSION_USER, cookieContent, 7 * 24 * 60 * 60);

        return user;
    }

    /**
     * 方法重写
     * <p>
     * 2015年8月2日 下午3:17:29 flyfox 369191470@qq.com
     *
     * @return
     */
    public void removeSessionUser() {
        removeSessionAttr(Constants.SESSION_USER);
        // 删除cookie
        removeCookie(Constants.SESSION_USER);
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
}
