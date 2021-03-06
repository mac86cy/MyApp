package com.kmia.nbfids.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.kmia.nbfids.utils.HouseKeepingWork;
import com.kmia.nbfids.utils.UpdateBaseWork;
import com.kmia.nbfids.utils.UpdateFlightsWork;
import com.kmia.nbfids.utils.UpdateSoftware;

import java.util.Timer;

/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：系统后台数据更新和清理服务
 *  
 */
public class UpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timer timer = new Timer();
        timer.schedule(new UpdateBaseWork(this), 0, 24 * 60 * 60 * 1000);// 更新基础数据，每天
        timer.schedule(new UpdateFlightsWork(this), 0, 60 * 1000);// 更新航班动态数据,每分钟
        timer.schedule(new HouseKeepingWork(), 0, 24 * 60 * 60 * 1000); // 清理历史航班数据，每天
        timer.schedule(new UpdateSoftware(this), 10 * 1000, 7 * 24 * 60 * 60 * 1000); //软件更新服务,7天
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("UPDATE-SERVICE", "更新服务开启");
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("UPDATE-SERVICE", "更新服务退出");
        super.onDestroy();
    }
}
