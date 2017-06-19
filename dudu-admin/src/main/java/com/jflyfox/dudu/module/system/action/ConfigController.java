package com.jflyfox.dudu.module.system.action;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysConfig;
import com.jflyfox.dudu.module.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 系统配置表 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/config")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService service;

    private static final String path = "/pages/system/config/config_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        view.addObject("selectOption", service.selectType(0L));
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam Long type, @RequestParam Integer operType) {
        ModelAndView view = new ModelAndView(path + "add.html");
        view.addObject("selectOption", service.selectType(type));
        view.addObject("operType", operType);
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id, @RequestParam Integer operType) {
        SysConfig model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        view.addObject("operType", operType);
        view.addObject("selectOption", service.selectType(model.getType()));
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysConfig model = service.selectById(id);
        // 父类名称
        SysConfig parent = service.selectById(model.getType());
        model.setTypeName(parent != null ? parent.getName() : null);

        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        SysConfig model = new SysConfig();
        Long userid = getSessionUser().getId();
        String now = getNow();
        model.setUpdateId(userid);
        model.setUpdateTime(now);
        model.setId(id);

        if (!service.deleteByIdLog(model)) {
            return fail("删除失败");
        }

        return success();
    }

    @SameUrlData
    @RequestMapping(value = "/save/{id}")
    public Object save(@PathVariable Long id, @ModelAttribute SysConfig model) {
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
        PageInfo<SysConfig> pageData = service.selectConfigPage(query);
        return getJqgridList(pageData);
    }

}