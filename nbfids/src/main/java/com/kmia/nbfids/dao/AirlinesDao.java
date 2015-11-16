package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.basic.Airlines;
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
 *  * 类说明：航空公司数据库业务逻辑
 *  
 */
public class AirlinesDao {

    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);


    /**
     *
     * @param iataCode 根据iataCode获得航空公司对象
     * @return 航空公司
     */
    public Airlines getAirlineByIataCode(String iataCode) {
        Airlines airline = new Airlines();

        try {
            airline = db.selector(Airlines.class).where("fiataCode", "=", iataCode).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }

        return airline;
    }

    /**
     * @param fid 根据fid获取该条记录
     * @return 航空公司对象
     */
    public Airlines getAirlineById(String fid) {
        Airlines airline = new Airlines();
        try {
            airline = db.selector(Airlines.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return airline;
    }

    /**
     * 增加一条航空公司记录
     *
     * @param airline 增加航空公司
     */
    public void addAirline(Airlines airline) {
        try {
            db.save(airline);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param airlines 批量增加航空公司
     */
    public void addAirlines(List<Airlines> airlines) {
        try {
            db.save(airlines);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id 根据id删除一条航空公司记录
     */
    public void delAirline(int id) {
        try {
            db.deleteById(Airlines.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空航空公司记录表
     */
    public void clearAirlines() {
        try {
            db.delete(Airlines.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param airline 更新airline这条记录
     */
    public void updateAirline(Airlines airline) {
        if (airline != null) {
            delAirline(getAirlineById(airline.getFid()).getId());
            addAirline(airline);
        }
    }

    /**
     * @param airlines 更新airlines所有记录
     */
    public void updateAirlines(List<Airlines> airlines) {
        if (airlines != null && airlines.size() > 0) {
            clearAirlines();
            addAirlines(airlines);
        }
    }

    /**
     * @return 显示表所有记录
     */
    public List<Airlines> listAirlines() {
        List<Airlines> list = new ArrayList<>();
        try {
            list = db.selector(Airlines.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
