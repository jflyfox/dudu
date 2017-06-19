package com.jflyfox.dudu.module.system.action;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.JqgridBean;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysDictDetail;
import com.jflyfox.dudu.module.system.service.IDictdetailService;
import com.jflyfox.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 数据字典 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/dictdetail")
public class DictdetailController extends BaseController {

    @Autowired
    private IDictdetailService service;

    private static final String path = "/pages/system/dictdetail/dictdetail_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam String dictType) {
        ModelAndView view = new ModelAndView(path + "add.html");
        SysDictDetail model = new SysDictDetail();
        model.setDictType(dictType);
        view.addObject("model", model);
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        SysDictDetail model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysDictDetail model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        SysDictDetail model = new SysDictDetail();
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
    public Object save(@PathVariable Long id, @ModelAttribute SysDictDetail model) {
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
        PageInfo<SysDictDetail> pageData = service.selectDictdetailPage(query);
        return getJqgridList(pageData);
    }

    @RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable Long id, SysDictDetail attr) {
        SysDictDetail model = null;
        if (id > 0L) {
            model = service.selectById(id);
        } else if (StrUtils.isNotEmpty(attr.getDictType()) && StrUtils.isNotEmpty(attr.getCode())) {
            Wrapper<SysDictDetail> wrapper = new EntityWrapper<>();
            wrapper.eq("dict_type", attr.getDictType()).eq("code", attr.getCode());
            model = service.selectOne(wrapper);
        }
        return success(model);
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public Object get(SysDictDetail attr) {
        Wrapper<SysDictDetail> wrapper = new EntityWrapper<>();
        if (StrUtils.isNotEmpty(attr.getDictType())) {
            wrapper.eq("dict_type", attr.getDictType());
        }
        wrapper.orderBy("sort,id desc");
        List<SysDictDetail> list = service.selectList(wrapper);
        return success(list);
    }

}