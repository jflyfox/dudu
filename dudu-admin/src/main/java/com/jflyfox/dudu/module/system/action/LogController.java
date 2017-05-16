package com.jflyfox.dudu.module.system.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysLog;
import com.jflyfox.dudu.module.system.service.ILogService;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/log")
public class LogController extends BaseController {

    @Autowired
    private ILogService service;

    private static final String path = "/pages/system/log/log_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        return view;
    }

    @RequestMapping(value = "/jqgrid")
    public Object jqgrid(JqgridBean bean, @ModelAttribute SysLog attr) {
        Wrapper<SysLog> wrapper = new EntityWrapper<>();
        if (StrUtils.isNotEmpty(attr.getOperObject())) {
            wrapper.like("t.oper_object", attr.getOperObject());
        }
        if (StrUtils.isNotEmpty(attr.getOperTable())) {
            wrapper.like("t.oper_table", attr.getOperTable());
        }
        if (attr.getLogType() != null && attr.getLogType() > 0) {
            wrapper.eq("t.log_type", attr.getLogType());
        }
        if (StrUtils.isNotEmpty(bean.getOrderBy())) {
            wrapper.orderBy("t." + bean.getOrderBy());
        } else {
            wrapper.orderBy("t.id desc");
        }

        Page<SysLog> pageData = service.selectLogPage(bean.getPagination(), wrapper);

        return getJqgridList(pageData);
    }

}