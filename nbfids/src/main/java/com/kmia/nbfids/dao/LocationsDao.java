package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.basic.Locations;
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
 *  * 类说明：地名数据库业务逻辑
 *  
 */
public class LocationsDao {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);

    public Locations getLocationByIataCode(String iataCode) {
        Locations location = new Locations();
        try {
            location = db.selector(Locations.class).where("fiataCode", "=", iataCode).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     * @param fid 根据fid
     * @return 返回记录
     */
    public Locations getLocationById(String fid) {
        Locations location = new Locations();
        try {
            location = db.selector(Locations.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return location;
    }

    /**
     * @param location 增加一条记录
     */
    public void addLocation(Locations location) {
        try {
            db.save(location);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param locations 批量增加记录
     */
    public void addLocations(List<Locations> locations) {
        try {
            db.save(locations);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id 根据id删除一条记录
     */
    public void delLocation(int id) {
        try {
            db.deleteById(Locations.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空表
     */
    public void clearLocations() {
        try {
            db.delete(Locations.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param location 更新Locations这条记录
     */
    public void updateLocation(Locations location) {
        if (location != null) {
            delLocation(getLocationById(location.getFid()).getId());
            addLocation(location);
        }
    }

    /**
     * @param locations 更新Location所有记录
     */
    public void updateLocations(List<Locations> locations) {
        if (locations != null && locations.size() > 0) {
            clearLocations();
            addLocations(locations);
        }
    }

    /**
     * @return 显示表所有记录
     */
    public List<Locations> listLocation() {
        List<Locations> list = new ArrayList<>();
        try {
            list = db.selector(Locations.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
