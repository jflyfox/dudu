package com.jflyfox.dudu.component.base;

import com.baomidou.mybatisplus.service.IService;
import com.jflyfox.dudu.module.system.model.LogOperType;
import com.jflyfox.dudu.module.system.model.SysUser;

/**
 * Service基类接口
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/5/9.
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * 插入记录，并加入日志
     *
     * @param entity
     * @return
     */
    boolean insertLog(T entity);

    /**
     * 更新记录，并加入日志
     *
     * @param entity
     * @return
     */
    boolean updateByIdLog(T entity);

    /**
     * 删除记录，并加入日志
     *
     * @param entity
     * @return
     */
    boolean deleteByIdLog(T entity);

    /**
     * 保存系统日志
     *
     * @param user
     * @param operType
     */
    void saveSystemLog(Long userId, LogOperType operType);

    /**
     * 保存操作日志
     *
     * @param entity
     * @param operType
     */
    void saveOperLog(T entity, LogOperType operType);

}
