package com.kmia.nbfids.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.kmia.nbfids.utils.HouseKeepingWork;
import com.kmia.nbfids.utils.UpdateBaseWork;
import com.kmia.nbfids.utils.UpdateFlightsWork;

import java.util.Timer;

/**
 * Created by mac86cy on 15/11/14.
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
        timer.schedule(new UpdateFlightsWork(), 0, 60 * 60 * 1000);// 更新航班动态数据,每小时
        timer.schedule(new HouseKeepingWork(), 0, 24 * 60 * 60 * 1000); // 清理历史航班数据，每天
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
