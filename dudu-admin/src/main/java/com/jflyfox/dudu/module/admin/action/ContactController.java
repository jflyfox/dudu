package com.jflyfox.dudu.module.admin.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.admin.service.IContactService;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 联系人 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-10.
 */
@RestController
@RequestMapping(value = "/admin/contact")
public class ContactController extends BaseController {

    @Autowired
    private IContactService service;

    private static final String path = "/pages/admin/contact/contact_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView(path + "add.html");
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        TbContact model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        TbContact model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        TbContact model = new TbContact();
        Long userid = getSessionUser().getId();
        String now = getNow();
        model.setUpdateId(userid);
        model.setUpdateTime(now);
        model.setCreateId(userid);
        model.setCreateTime(now);
        model.setId(id);

        if (!service.deleteByIdLog(model)) {
            return fail("删除失败");
        }

        return success();
    }

    @SameUrlData
    @RequestMapping(value = "/save/{id}")
    public Object save(@PathVariable Long id, @ModelAttribute TbContact model) {
        Long userid = getSessionUser().getId();
        String now = getNow();
        model.setUpdateId(userid);
        model.setUpdateTime(now);
        if (id != null && id > 0) { // 更新
            service.updateByIdLog(model);
        } else { // 新增
            model.setCreateId(userid);
            model.setCreateTime(now);
            service.insertLog(model);
        }

        return success();
    }

    @RequestMapping(value = "/jqgrid")
    public Object jqgrid(JqgridBean bean, @ModelAttribute TbContact attr) {
        Wrapper<TbContact> wrapper = new EntityWrapper<>();
        if(StrUtils.isNotEmpty(attr.getName())) {
            wrapper.like("t.name",attr.getName());
        }
        if (StrUtils.isNotEmpty(bean.getOrderBy())) {
            wrapper.orderBy("t." + bean.getOrderBy());
        }

        Page<TbContact> pageData = service.selectContactPage(bean.getPagination(), wrapper);

        return getJqgridList(pageData);
    }

}