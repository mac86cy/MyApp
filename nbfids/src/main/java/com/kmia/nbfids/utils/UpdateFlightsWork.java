package com.kmia.nbfids.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
 *  * 类说明：航班数据更新任务类
 *  
 */
public class UpdateFlightsWork extends TimerTask {
    private Context context;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 9) {
                UpdateUtils.getTodayData(context);// 更新航班数据
                Log.d("UPDATE-START", "开始更新航班数据");
            }
        }
    };

    public UpdateFlightsWork(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = 9;
        handler.sendMessage(message);
    }
}
