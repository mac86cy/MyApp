package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.basic.Stands;
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
 *  * 类说明：机位数据库业务逻辑
 *  
 */
public class StandsDao {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);

    /**
     * @param code 根据code
     * @return 返回机位
     */
    public Stands getStandsbyCode(String code) {
        Stands stand = new Stands();
        try {
            stand = db.selector(Stands.class).where("fstand", "=", code).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return stand;
    }

    /**
     * @param fid 根据fid
     * @return 返回记录
     */
    public Stands getStandById(String fid) {
        Stands stand = new Stands();
        try {
            stand = db.selector(Stands.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return stand;
    }

    /**
     * @param stand 增加一条记录
     */
    public void addStand(Stands stand) {
        try {
            db.save(stand);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param stand 批量增加记录
     */
    public void addStands(List<Stands> stand) {
        try {
            db.save(stand);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id 根据id删除一条记录
     */
    public void delStand(int id) {
        try {
            db.deleteById(Stands.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空表
     */
    public void clearStands() {
        try {
            db.delete(Stands.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param stand 更新Stands这条记录
     */
    public void updateStand(Stands stand) {
        if (stand != null) {
            delStand(getStandById(stand.getFid()).getId());
            addStand(stand);
        }
    }

    /**
     * @param stands 更新Stand所有记录
     */
    public void updateStands(List<Stands> stands) {
        if (stands != null && stands.size() > 0) {
            clearStands();
            addStands(stands);
        }
    }

    /**
     * @return 显示表所有记录
     */
    public List<Stands> listStand() {
        List<Stands> list = new ArrayList<>();
        try {
            list = db.selector(Stands.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
