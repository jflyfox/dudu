package com.jflyfox.dudu.module.system.action;

import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseController;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.component.model.resubmit.SameUrlData;
import com.jflyfox.dudu.component.util.EncryptUtils;
import com.jflyfox.dudu.module.admin.model.TbContact;
import com.jflyfox.dudu.module.system.model.SysDepartment;
import com.jflyfox.dudu.module.system.model.SysRole;
import com.jflyfox.dudu.module.system.model.SysUser;
import com.jflyfox.dudu.module.system.service.IDepartmentService;
import com.jflyfox.dudu.module.system.service.IUserService;
import com.jflyfox.util.StrUtils;
import com.jflyfox.util.extend.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 用户 控制层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@RestController
@RequestMapping(value = "/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService service;
    @Autowired
    private IDepartmentService departmentService;

    private static final String path = "/pages/system/user/user_";

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView view = new ModelAndView(path + "list.html");
        // 下拉框
        view.addObject("selectDepartments", departmentService.selectDepart(0));
        return view;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView(path + "add.html");
        // 下拉框
        view.addObject("selectDepartments", departmentService.selectDepart(0));
        return view;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        SysUser model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "edit.html");
        view.addObject("model", model);
        // 下拉框
        view.addObject("selectDepartments", departmentService.selectDepart(model.getDepartid()));
        return view;
    }

    @RequestMapping(value = "/view/{id}")
    public ModelAndView view(@PathVariable Long id) {
        SysUser model = service.selectById(id);
        ModelAndView view = new ModelAndView(path + "view.html");
        SysDepartment department = departmentService.selectById(model.getDepartid());
        if (department != null) {
            model.setDepartName(department.getName());
        }
        view.addObject("model", model);
        return view;
    }

    @SameUrlData
    @RequestMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
        SysUser model = new SysUser();
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
    public Object save(@PathVariable Long id, @ModelAttribute SysUser model) {
        Long userid = getSessionUser().getId();
        String now = getNow();
        model.setUpdateId(userid);
        model.setUpdateTime(now);
        if (id != null && id > 0) { // 更新
            service.updateByIdLog(model);
        } else { // 新增
            if (StrUtils.isEmpty(model.getPassword())) {
                model.setPassword(EncryptUtils.defaultPassword());
            }
            model.setUuid(UuidUtils.getUUID2());
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
        PageInfo<SysUser> pageData = service.selectUserPage(query);
        return getJqgridList(pageData);
    }

    /**
     * 跳转到授权页面
     * <p>
     * 2015年4月28日 下午12:00:05 flyfox 369191470@qq.com
     */
    @RequestMapping(value = "/auth/{userid}")
    public ModelAndView auth(@PathVariable Long userid) {
        List<SysRole> roleList = service.getSysRoleList();
        String roleids = service.getRoleids(userid);

        ModelAndView view = new ModelAndView(path + "auth.html");

        view.addObject("userid", userid);
        view.addObject("roleids", roleids);
        view.addObject("list", roleList);
        return view;
    }

    /**
     * 保存角色信息
     * <p>
     * 2015年4月28日 下午3:18:33 flyfox 369191470@qq.com
     */
    @RequestMapping(value = "/authSave")
    public Object authSave(@RequestParam Long userid, @RequestParam String roleids) {
        service.saveUserRole(userid, roleids);
        return success();
    }

}