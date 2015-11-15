package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.basic.FlightStatus;
import com.kmia.nbfids.utils.Constants;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：航班状态数据库业务逻辑
 *  
 */
public class FlightStatusDao {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);

    /**
     * @param code 根据code
     * @return 返回记录
     */
    public FlightStatus getStatusByCode(String code) {
        FlightStatus status = new FlightStatus();
        try {
            status = db.selector(FlightStatus.class).where("fstatus", "=", code).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * @param fid 根据fid
     * @return 返回记录
     */
    public FlightStatus getStatusById(String fid) {
        FlightStatus status = new FlightStatus();
        try {
            status = db.selector(FlightStatus.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * 增加一条记录
     *
     * @param status
     */
    public void addStatus(FlightStatus status) {
        try {
            db.save(status);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量增加记录
     *
     * @param status
     */
    public void addStatuss(List<FlightStatus> status) {
        try {
            db.save(status);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id 根据id删除一条记录
     */
    public void delStatus(int id) {
        try {
            db.deleteById(FlightStatus.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空表
     */
    public void clearStatuss() {
        try {
            db.delete(FlightStatus.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param status 更新FlightStatus这条记录
     */
    public void updateStatus(FlightStatus status) {
        if (status != null) {
            delStatus(getStatusById(status.getFid()).getId());
            addStatus(status);
        }
    }

    /**
     * @param status 更新status所有记录
     */
    public void updateStatuss(List<FlightStatus> status) {
        if (status.size() > 0 && status != null) {
            clearStatuss();
            addStatuss(status);
        }
    }

    /**
     * @return 显示表所有记录
     */
    public List<FlightStatus> listStatus() {
        List<FlightStatus> list = new ArrayList<FlightStatus>();
        try {
            list = db.selector(FlightStatus.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
