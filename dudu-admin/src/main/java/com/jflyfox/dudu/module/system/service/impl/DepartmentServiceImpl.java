package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.component.config.mybatis.DBTypeEnum;
import com.jflyfox.dudu.component.config.mybatis.DbContextHolder;
import com.jflyfox.dudu.component.model.Query;
import com.jflyfox.dudu.module.system.dao.DepartmentMapper;
import com.jflyfox.dudu.module.system.model.SysDepartment;
import com.jflyfox.dudu.module.system.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-04-23.
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, SysDepartment> implements IDepartmentService {

    public PageInfo<SysDepartment> selectDepartmentPage(Query query) {
        // DbContextHolder.setDbType(DBTypeEnum.admin);
        PageHelper.startPage(query.getPage(), query.getRows());
        PageInfo<SysDepartment> pageInfo = new PageInfo<SysDepartment>(baseMapper.selectDepartmentPage(query));
        return pageInfo;
    }

    public String selectDepart(long selected) {
        return selectDepart(selected, 0);
    }

    public String selectDepart(long selected, long selfId) {
        String where = " 1 = 1 ";
        if (selfId > 0) {
            where += "and id !=" + selfId;
        }

        Wrapper<SysDepartment> wrapper = new EntityWrapper<>();
        wrapper.where(where).orderBy("sort,create_time desc");
        List<SysDepartment> list = selectList(wrapper);

        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (SysDepartment department : list) {
                int parentId = department.getParentid();
                if (parentId == 0) {
                    long id = department.getId();
                    sb.append("<option value=\"");
                    sb.append(id);
                    sb.append("\" ");
                    boolean flag = (id == selected);
                    sb.append(flag ? "selected" : "");
                    sb.append(">");
                    sb.append(department.getName());
                    sb.append("</option>");

                    sb.append(recursionTree(id, selected, 1, list));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 递归树
     * <p>
     * 2017年3月14日 上午10:39:29
     * flyfox 330627517@qq.com
     *
     * @return
     */
    private String recursionTree(long rootId, long selected, int level, List<SysDepartment> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }

        // 级别太多了
        if (level >= 20) {
            return "";
        }

        StringBuffer sb = new StringBuffer("");
        for (SysDepartment department : list) {
            int parentId = department.getParentid();
            if (parentId == rootId) {
                long id = department.getId();
                sb.append("<option value=\"");
                sb.append(id);
                sb.append("\" ");
                boolean flag = (id == selected);
                sb.append(flag ? "selected" : "");
                sb.append(">");
                for (int i = 0; i < level; i++) {
                    if (i == (level - 1)) {
                        sb.append("&nbsp;|--");
                    } else {
                        sb.append("&nbsp;&nbsp;&nbsp;");
                    }
                }
                sb.append(department.getName());
                sb.append("</option>");

                sb.append(recursionTree(id, selected, (level + 1), list));
            }
        }

        return sb.toString();
    }

}