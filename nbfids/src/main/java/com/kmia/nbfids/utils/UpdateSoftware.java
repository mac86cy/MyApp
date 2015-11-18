package com.kmia.nbfids.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.TimerTask;

/**
 * Copyright 2015 KMIA. All rights reserved. 
 * 作者 ：mac86cy
 * 邮箱 ：mac86cy@163.com
 * 创建时间：2015/11/18 16:07
 * 类说明：软件更新计划任务
 */
public class UpdateSoftware extends TimerTask {
    private Context context;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 6) {
                UpdateUtils.getVersion(context);// 更新基础数据
                Log.d("UPDATE-START", "开始检查更新");

            }
        }
    };

    public UpdateSoftware(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = 6;
        handler.sendMessage(message);
    }
}
