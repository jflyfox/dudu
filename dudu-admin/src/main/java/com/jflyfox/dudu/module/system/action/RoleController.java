package com.jflyfox.dudu.module.system.action;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.model.SysRole;
import com.jflyfox.dudu.module.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService service;

    private static final String path = "/pages/system/role/role_";

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
        SysRole model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysRole model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "view.html");
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        SysRole model = new SysRole();
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
    public Object save(@PathVariable Long id, @ModelAttribute SysRole model) {
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
        PageInfo<SysRole> pageData = service.selectRolePage(query);
        return getJqgridList(pageData);
    }

    /**
     * 跳转到授权页面
     * <p>
     * 2015年4月28日 下午12:00:05 flyfox 369191470@qq.com
     */
    @RequestMapping(value = "/auth/{roleid}")
    public ModelAndView auth(@PathVariable Long roleid) {
        String menuids = service.getMenuids(roleid);

        ModelAndView view = new ModelAndView(path + "auth.html");
        // 获取根目录
        List<SysMenu> rootList = service.getListByParentid(0L);
        // 获取子目录
        Map<Long, List<SysMenu>> map = new HashMap<Long, List<SysMenu>>();
        rootList.forEach(sysMenu -> {
            map.put(sysMenu.getId(), service.getListByParentid(sysMenu.getId()));
        });

        view.addObject("roleid", roleid);
        view.addObject("menus", menuids);
        // 根结点
        view.addObject("rootList", rootList);
        // 二级目录
        view.addObject("map", map);
        return view;
    }

    /**
     * 保存绑定信息
     * <p>
     * 2015年4月28日 下午3:18:33 flyfox 369191470@qq.com
     */
    @RequestMapping(value = "/authSave")
    public Object authSave(@RequestParam Long roleid, @RequestParam String menus) {
        service.saveRoleMenu(roleid, menus);
        return success();
    }

}