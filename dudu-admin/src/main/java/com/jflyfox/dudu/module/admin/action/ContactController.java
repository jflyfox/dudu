package com.jflyfox.dudu.module.admin.action;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.admin.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
    public Object jqgrid(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        PageInfo<TbContact> pageData = service.selectContactPage(query);
        return getJqgridList(pageData);
    }

}