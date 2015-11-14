package com.kmia.nbfids.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.TimerTask;

/**
 * Created by mac86cy on 15/11/14.
 */
public class UpdateBaseWork extends TimerTask {
    private Context context;
    private SharePreference util;
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
