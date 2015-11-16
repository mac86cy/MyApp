package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.Arrivals;
import com.kmia.nbfids.utils.Constants;
import com.kmia.nbfids.utils.DateFormat;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
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
 *  * 类说明：到港数据库业务逻辑
 *  
 */
public class ArrivalsDao {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);

    /**
     * @param fid 根据fid获取该条航班信息
     * @return 航班
     */
    public Arrivals getArrivalByFid(String fid) {
        Arrivals flight = new Arrivals();
        try {
            flight = db.selector(Arrivals.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flight;
    }

    /**
     * @param fid 根据fid删除一条记录
     */
    public void delArrivalByFid(String fid) {
        if (fid != null && !fid.equals("")) {
            try {
                db.delete(Arrivals.class, WhereBuilder.b("fid", "=", fid));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 逐条增加
     */
    public void addArrival(Arrivals flight) {
        try {
            db.save(flight);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全部添加
     */
    public void addArrivals(List<Arrivals> flights) {
        try {
            db.save(flights);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 逐条删除
     */
    public void delArrival(int id) {
        try {
            db.deleteById(Arrivals.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空到港表
     */
    public void clearArrivals() {
        try {
            db.delete(Arrivals.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新到港表一条航班
     */
    public void updateArrival(Arrivals flight) {
        if (flight != null) {
            Arrivals old = getArrivalByFid(flight.getFid());
            if (old != null) {
                delArrival(old.getId());
            }
            addArrival(flight);
        }
    }

    /**
     * 更新表所有记录
     */
    public void updateArrivals(List<Arrivals> flights) {
        if (flights != null && flights.size() > 0) {
            clearArrivals();
            addArrivals(flights);
        }
    }

    /**
     * 显示表所有记录
     */
    public List<Arrivals> listArrivals() {
        List<Arrivals> list = new ArrayList<>();
        try {
            list = db.selector(Arrivals.class).where("fcla", "=", "W/Z")// 客班正班
                    .and("ffst", "=", "J")// 正常旅客服务 配合PAX
                    .and("fnat", "=", "PAX")// 客机
                    .and("ftof", "=", "D")// 国内航班
                    .and("fsta", "<", DateFormat.addHour(new Date(), Constants.TIME_QUANTUM))// 计划到达前4小时航班
                    .and("fsta", ">", DateFormat.addHour(new Date(), 0))// 测试时，减少显示
                    .and(WhereBuilder.b("ftdt", "=", null).or("ftdt", ">", DateFormat.addHour(new Date(), -1)))// 实际到达一小时后下屏，没有实际时间不下屏
                    .and(WhereBuilder.b("ffsa", "!=", "FX").or("ffsa", "!=", "NO").or("ffsa", "!=", "XF").or("ffsa",
                            "!=", "OV"))// 不显示归档、非营运、航班修复、复飞通畅的航班状态的航班
                    .orderBy("fsta").findAll();// 按计划到达时间排序
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 清理三天前的历史数据
     */
    public void HouseKeeping() {
        try {
            db.delete(Arrivals.class, WhereBuilder.b("fsdt", "<", DateFormat.getDateThree()));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
