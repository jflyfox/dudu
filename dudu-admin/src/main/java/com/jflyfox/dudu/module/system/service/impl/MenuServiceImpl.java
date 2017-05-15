package com.jflyfox.dudu.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jflyfox.dudu.component.base.BaseServiceImpl;
import com.jflyfox.dudu.module.system.dao.MenuMapper;
import com.jflyfox.dudu.module.system.model.SysMenu;
import com.jflyfox.dudu.module.system.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 服务层口层
 *
 * @author flyfox 369191470@qq.com on 2017-05-06.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, SysMenu> implements IMenuService {

    public Page<SysMenu> selectMenuPage(Page<SysMenu> page, Wrapper<SysMenu> wrapper) {
        page.setRecords(baseMapper.selectMenuPage(page, wrapper));
        return page;
    }

    public String selectMenu(long selected) {
        return selectMenu(selected, 0);
    }

    public String selectMenu(long selected, long selfId) {
        String where = " 1 = 1 ";
        if (selfId > 0) {
            where += "and id !=" + selfId;
        }

        Wrapper<SysMenu> wrapper = new EntityWrapper<>();
        wrapper.where(where).eq("parentid", 0).orderBy("sort,create_time desc");
        List<SysMenu> list = selectList(wrapper);

        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (SysMenu menu : list) {
                long parentId = menu.getParentid();
                if (parentId == 0L) {
                    long id = menu.getId();
                    sb.append("<option value=\"");
                    sb.append(id);
                    sb.append("\" ");
                    boolean flag = (id == selected);
                    sb.append(flag ? "selected" : "");
                    sb.append(">");
                    sb.append(menu.getName());
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
    private String recursionTree(long rootId, long selected, int level, List<SysMenu> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }

        // 级别太多了
        if (level >= 20) {
            return "";
        }

        StringBuffer sb = new StringBuffer("");
        for (SysMenu menu : list) {
            long parentId = menu.getParentid();
            if (parentId == rootId) {
                long id = menu.getId();
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
                sb.append(menu.getName());
                sb.append("</option>");

                sb.append(recursionTree(id, selected, (level + 1), list));
            }
        }

        return sb.toString();
    }
}