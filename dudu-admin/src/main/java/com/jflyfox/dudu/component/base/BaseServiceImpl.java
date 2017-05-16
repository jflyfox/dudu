package com.jflyfox.dudu.component.base;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jflyfox.dudu.module.system.model.*;
import com.jflyfox.dudu.module.system.service.IDictdetailService;
import com.jflyfox.dudu.module.system.service.ILogService;
import com.jflyfox.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.security.InvalidParameterException;

/**
 * Service基类接口
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/9.
 *
 * @param <M>
 * @param <T>
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ILogService logService;
    @Autowired
    IDictdetailService dictdetailService;


    public boolean insertLog(T entity) {
        boolean flag = super.insert(entity);
        logger.info("#####insertLog...");
        saveOperLog(entity, LogOperType.INSERT);
        return flag;
    }

    public boolean updateByIdLog(T entity) {
        boolean flag = super.updateById(entity);
        logger.info("#####updateByIdLog...");
        saveOperLog(entity, LogOperType.UPDATE);
        return flag;
    }

    public boolean deleteByIdLog(T entity) {
        Serializable id = null;
        if (entity instanceof BaseModel) {
            BaseModel model = (BaseModel) entity;
            id = model.pkValue();
        } else {
            throw new InvalidParameterException("entity is not BaseModel");
        }
        boolean flag = super.deleteById(id);
        logger.info("#####deleteByIdLog...");
        saveOperLog(entity, LogOperType.DELETE);
        return flag;
    }

    public void saveSystemLog(Long userId, LogOperType operType) {
        try {
            String tableName = "sys_user";
            String now = DateUtils.getNow(DateUtils.YMD_HMS);

            Wrapper<SysDictDetail> wrapper = new EntityWrapper<>();
            wrapper.eq("dict_type", "systemLog").eq("name", tableName);
            SysDictDetail dictDetail = dictdetailService.selectOne(wrapper);
            String operObject = dictDetail == null ? "" : dictDetail.getCode();

            SysLog log = new SysLog();
            log.setLogType(LogType.SYSTEM.getValue());
            log.setOperId(userId);
            log.setOperTable(tableName);
            log.setOperObject(operObject);
            log.setOperType(operType.getValue());
            log.setUpdateId(userId);
            log.setUpdateTime(now);
            log.setCreateId(userId);
            log.setCreateTime(now);
            logService.insert(log);
        } catch (Exception e) {
            logger.error("添加日志失败", e);
        }
    }

    public void saveOperLog(T entity, LogOperType operType) {
        try {
            if (entity instanceof BaseModel) {
                TableInfo tableInfo = SqlHelper.table(currentModleClass());
                String tableName = tableInfo.getTableName();
                String keyProperty = tableInfo.getKeyProperty();
                BaseModel model = (BaseModel) entity;
                Long primaryKey = (Long) model.pkValue();

                Wrapper<SysDictDetail> wrapper = new EntityWrapper<>();
                wrapper.eq("dict_type", "systemLog").eq("name", tableName);
                SysDictDetail dictDetail = dictdetailService.selectOne(wrapper);
                String operObject = dictDetail == null ? "" : dictDetail.getCode();


                SysLog log = new SysLog();
                log.setLogType(LogType.OPERATE.getValue());
                log.setOperId(primaryKey);
                log.setOperTable(tableName);
                log.setOperObject(operObject);
                log.setOperType(operType.getValue());
                log.setUpdateId(model.getUpdateId());
                log.setUpdateTime(model.getUpdateTime());
                log.setCreateId(model.getUpdateId());
                log.setCreateTime(model.getUpdateTime());
                logService.insert(log);
            }
        } catch (Exception e) {
            logger.error("添加日志失败", e);
        }
    }

}
