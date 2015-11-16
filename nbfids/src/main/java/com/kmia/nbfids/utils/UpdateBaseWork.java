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
 *  * 类说明：基础数据更新任务类
 *  
 */
public class UpdateBaseWork extends TimerTask {
    private Context context;
    private SharePreference util;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 8) {
                if (util.getisFirst()) {// 第一次开启，请求基础数据
                    UpdateUtils.getBaseAll(context);// 更新基础数据
                }
                Log.d("SERVICE-WORK", "执行更新基础数据");
            }
        }
    };

    public UpdateBaseWork(Context context) {
        super();
        this.context = context;
        util = new SharePreference(context, Constants.SAVE_USER);
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = 8;
        handler.sendMessage(message);
    }
}
