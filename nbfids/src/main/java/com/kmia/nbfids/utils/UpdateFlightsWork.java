package com.kmia.nbfids.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.TimerTask;

/**
 * Created by mac86cy on 15/11/14.
 */
public class UpdateFlightsWork extends TimerTask {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 9) {
                UpdateUtils.getTodayData();// 更新航班数据
                Log.d("SERVICE-WORK", "执行更新航班数据");
            }
        }
    };

    @Override
    public void run() {
        Message message = new Message();
        message.what = 9;
        handler.sendMessage(message);
    }
}
