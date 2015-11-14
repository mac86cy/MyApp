package com.kmia.nbfids.dao;

import com.kmia.nbfids.model.Departures;
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
 * Created by mac86cy on 15/11/14.
 */
public class DeparturesDao {
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName(Constants.DBNAME)
            .setDbVersion(1);

    DbManager db = x.getDb(daoConfig);

    /**
     * @param fid 根据fid获取该条航班信息
     * @return
     */
    public Departures getDepartureByFid(String fid) {
        Departures flight = new Departures();
        try {
            flight = db.selector(Departures.class).where("fid", "=", fid).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return flight;
    }

    /**
     * @param fid 根据fid 删除一条记录
     */
    public void delDepartureByFid(String fid) {
        if (fid != null && !fid.equals("")) {
            try {
                db.delete(Departures.class, WhereBuilder.b("fid", "=", fid));
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 逐条增加
     */
    public void addDeparture(Departures flight) {
        try {
            db.save(flight);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全部添加
     */
    public void addDepartures(List<Departures> flights) {
        try {
            db.save(flights);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 逐条删除
     */
    public void delDeparture(int id) {
        try {
            db.deleteById(Departures.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清空出港表
     */
    public void clearDepartures() {
        try {
            db.delete(Departures.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新出港表一条航班
     *
     * @param flight
     */
    public void updateDeparture(Departures flight) {
        if (flight != null) {
            Departures old = getDepartureByFid(flight.getFid());
            if (old != null) {
                delDeparture(old.getId());
            }
            addDeparture(flight);
        }
    }

    /**
     * 更新表所有记录
     */
    public void updateDepartures(List<Departures> flights) {
        if (flights.size() > 0 && flights != null) {
            clearDepartures();
            addDepartures(flights);
        }
    }

    /**
     * 显示表所有记录
     */
    public List<Departures> listDepartures() {
        List<Departures> list = new ArrayList<Departures>();
        try {
            list = db.selector(Departures.class).where("fcla", "=", "W/Z")// 客班正班
                    .and("ffst", "=", "J")// 正常旅客服务 配合PAX
                    .and("fnat", "=", "PAX")// 客机
                    .and("ftof", "=", "D")// 国内航班
                    .and("fstd", "<", DateFormat.addHour(new Date(), Constants.TIME_QUANTUM))// 计划到达前4小时航班
                    .and("fabt", "=", null)// 收到起飞时间下屏
                    .and(WhereBuilder.b("ffsa", "!=", "FX").or("ffsa", "!=", "NO").or("ffsa", "!=", "XF").or("ffsa",
                            "!=", "OV"))// 不显示归档、非营运、航班修复、复飞通畅的航班状态的航班
                    .orderBy("fstd").findAll();// 按计划到达时间排序
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
            db.delete(Departures.class, WhereBuilder.b("fsdt", "<", DateFormat.getDateThree()));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
