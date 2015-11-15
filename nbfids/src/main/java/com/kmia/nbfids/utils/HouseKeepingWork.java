package com.kmia.nbfids.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.kmia.nbfids.dao.ArrivalsDao;
import com.kmia.nbfids.dao.DeparturesDao;

import java.util.TimerTask;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：数据库清理任务
 *  
 */
public class HouseKeepingWork extends TimerTask {
    private ArrivalsDao arrivalsDao;
    private DeparturesDao departuresDao;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 7) {
                arrivalsDao.HouseKeeping();
                departuresDao.HouseKeeping();
                Log.d("SERVICE-WORK", "执行清理数据");
            }
        }
    };

    public HouseKeepingWork() {
        super();
        arrivalsDao = new ArrivalsDao();
        departuresDao = new DeparturesDao();
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = 7;
        handler.sendMessage(message);
    }
}
