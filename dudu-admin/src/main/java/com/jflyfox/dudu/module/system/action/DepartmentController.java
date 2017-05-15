package com.jflyfox.dudu.module.system.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysDepartment;
import com.jflyfox.dudu.module.system.service.IDepartmentService;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 组织机构管理
 *
 * @author flyfox 2014-2-11
 */
@RestController
@RequestMapping(value = "/system/department")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService service;

    private static final String path = "/pages/system/department/department_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam("parentId") Long parentId) {
        ModelAndView view = new ModelAndView(path + "add.html");
        // 下拉框
        view.addObject("selectParents", service.selectDepart(parentId == null ? 0 : parentId));
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        SysDepartment model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        // 下拉框
        view.addObject("selectParents", service.selectDepart(model.getParentid(), model.getId()));
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysDepartment model = service.selectById(id);
        // 父类名称
        SysDepartment department = service.selectById(model.getParentid());
        model.setParentName(department != null ? department.getName() : null);

        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id, @ModelAttribute SysDepartment attr) {
        SysDepartment model = new SysDepartment();
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
    public Object save(@PathVariable Long id, @ModelAttribute("model") SysDepartment model) {
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

    @RequestMapping(value = "/tree")
    public List<SysDepartment> tree() {
        Wrapper<SysDepartment> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort,create_time desc");
        List<SysDepartment> list = service.selectList(wrapper);
        return list;
    }

    @RequestMapping(value = "/jqgrid")
    public Object jqgrid(JqgridBean bean, @ModelAttribute SysDepartment sysDepartment) {
        Wrapper<SysDepartment> wrapper = new EntityWrapper<SysDepartment>();
        if (StrUtils.isNotEmpty(sysDepartment.getName())) {
            wrapper.like("t.name", sysDepartment.getName());
        }
        if (StrUtils.isNotEmpty(bean.getOrderBy())) {
            wrapper.orderBy("t." + bean.getOrderBy());
        }

        Page<SysDepartment> pageData = service.selectDepartmentPage(bean.getPagination(), wrapper);

        return getJqgridList(pageData);
    }

}
