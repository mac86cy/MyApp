package com.kmia.nbfids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kmia.nbfids.R;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015 17:50
 *  *
 *  * 类说明：
 *  
 */
public class WelcomeActivity extends Activity {

    private Handler mHandler;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreenDisplay();// 全屏显示
        setContentView(R.layout.activity_welcome);
        startService();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                goMain();
            }
        }, 1000);
    }

    /**
     * 开启服务
     */
    private void startService() {
        Intent intent = new Intent(this, UpdateService.class);
        startService(intent);// 开启后台更新服务
    }

    /**
     * 全屏显示
     */
    private void fullScreenDisplay() {
        window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // 全屏显示，不显示虚拟按键
        window.setAttributes(params);
    }

    /**
     * 跳转到主页面
     */
    private void goMain() {
        Intent intent = new Intent(this, ArrivalsActivity.class);
        startActivity(intent);
        finish();
    }
}
