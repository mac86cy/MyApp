package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.basic.Airlines;
import com.kmia.nbfids.utils.Constants;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac86cy on 15/11/14.
 */
public class AirlinesDao {

    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);


    /**
     * @param iataCode 根据iataCode获得航空公司对象
     * @return
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
     * @param airline
     */
    public void addAirline(Airlines airline) {
        try {
            db.save(airline);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量增加航空公司
     *
     * @param airlines
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
        if (airlines.size() > 0 && airlines != null) {
            clearAirlines();
            addAirlines(airlines);
        }
    }

    /**
     * @return 显示表所有记录
     */
    public List<Airlines> listAirlines() {
        List<Airlines> list = new ArrayList<Airlines>();
        try {
            list = db.selector(Airlines.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
}
