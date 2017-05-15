package com.jflyfox.dudu.module.system.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.service.IMenuService;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 菜单 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService service;

    private static final String path = "/pages/system/menu/menu_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam("parentId") Long parentId) {
        ModelAndView view = new ModelAndView(path + "add.html");
        // 下拉框
        view.addObject("selectParents", service.selectMenu(parentId == null ? 0 : parentId));
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        SysMenu model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        // 下拉框
        view.addObject("selectParents", service.selectMenu(model.getParentid(), model.getId()));
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysMenu model = service.selectById(id);
        // 父类名称
        SysMenu menu = service.selectById(model.getParentid());
        model.setParentName(menu != null ? menu.getName() : null);

        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        SysMenu model = new SysMenu();
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
    public Object save(@PathVariable Long id, @ModelAttribute SysMenu model) {
        Long userid = getSessionUser().getId();
        String now = getNow();
        model.setUpdateId(userid);
        model.setUpdateTime(now);

        // 根目录级别为1
        Long parentid = model.getParentid();
        if (parentid != null) {
            model.setLevel(parentid == 0 ? 1 : 2);
        }

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
    public List<SysMenu> tree() {
        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.orderBy("sort,create_time desc");
        List<SysMenu> list = service.selectList(wrapper);
        return list;
    }

    @RequestMapping(value = "/jqgrid")
    public Object jqgrid(JqgridBean bean, @ModelAttribute SysMenu attr) {
        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
//        if(StrUtils.isNotEmpty(attr.getName())) {
//            wrapper.like("t.name",attr.getName());
//        }
        if (StrUtils.isNotEmpty(bean.getOrderBy())) {
            wrapper.orderBy("t." + bean.getOrderBy());
        }

        Page<SysMenu> pageData = service.selectMenuPage(bean.getPagination(), wrapper);

        return getJqgridList(pageData);
    }

}