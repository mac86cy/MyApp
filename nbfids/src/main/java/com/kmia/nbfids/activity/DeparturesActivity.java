package com.kmia.nbfids.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.kmia.nbfids.R;
import com.kmia.nbfids.dao.DeparturesDao;
import com.kmia.nbfids.model.Departures;
import com.kmia.nbfids.utils.Constants;

import java.util.List;

/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：离港航班引导页面
 *  
 */
public class DeparturesActivity extends Activity {

    public static DeparturesActivity instance = null;
    private static int isOpen = 0;
    private DeparturesAdapter adapter;// 数据适配器，用于填充数据
    /**
     * 主UI线程代理，用来监听msg，获取消息更新主线程UI
     */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<Departures> data;
            switch (msg.what) {
                case 0:
                    data = (List<Departures>) msg.obj;
                    adapter.update(data, msg.arg1);// 更新数据适配器的数据，和中英文标志位
                    break;
                default:
                    break;
            }
        }
    };
    private DeparturesDao dao;// 数据接口
    private Handler handler;
    private Runnable runnable;
    private List<Departures> list;// 每次从数据接口取来的list，即N小时内的航班
    private List<Departures> subList;// list的子list，用于将list切割为每页所显示的数据
    private int listSize;// list的大小，list.size()
    private int pageSize;// 页面数量，listsize/rows，如果有余数+1
    private Thread thread;// 子线程，用于循环翻页
    private long exitTime = 0;
    private MyBroadcastReciver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreenDisplay();// 全屏显示
        setContentView(R.layout.departures);
        registerReciver();//注册广播接受者
        initView();// 初始化listview
        timingRefresh();// 定时刷新
        instance = this;
    }

    private void registerReciver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.SOFTWARE_UPDATE_ACTION);
        intentFilter.addAction(Constants.NETWORK_ERROR_ACTION);
        receiver = new MyBroadcastReciver();
        this.registerReceiver(receiver, intentFilter);
    }

    /**
     * 定时刷新数据，页面翻页
     */
    private void timingRefresh() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                list = dao.listDepartures();
                if (list != null) {
                    listSize = list.size();
                    pageSize = listSize % Constants.ROWS == 0 ? listSize / Constants.ROWS : listSize / Constants.ROWS + 1;// 取余，余数不为零+1页
                    thread = new Thread() {
                        @Override
                        public void run() {
                            for (int i = 1; i <= pageSize; i++) {// 根据分页，进行循环
                                if (i != pageSize) {// 不为最后一页就从rows*(n-1)到rows*n
                                    subList = list.subList((i - 1) * Constants.ROWS, i * Constants.ROWS);
                                } else {// 最后一页的sublist结束index为最后一个
                                    subList = list.subList((i - 1) * Constants.ROWS, listSize);
                                }
                                try {
                                    for (int j = 0; j < 2; j++) {// 中英文翻页循环，0为中文，1为英文
                                        Message msg = new Message();// 创建消息
                                        msg.obj = subList;// 消息体，用来通知UI主线程更新的list
                                        msg.arg1 = j;// 中英文标志位
                                        mHandler.sendMessage(msg);
                                        Thread.sleep(Constants.INTERVAL * 1000);// 每页显示时长
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    thread.start();// 开始线程
                }
                handler.postDelayed(this, 2 * pageSize * Constants.INTERVAL * 1000);
            }
        };
        handler.postDelayed(runnable, 2 * pageSize * Constants.INTERVAL * 1000);
    }

    /**
     * 初始化页面
     */
    private void initView() {
        ListView lv = (ListView) findViewById(R.id.departure_list);
        dao = new DeparturesDao();
        adapter = new DeparturesAdapter(this, dao.listDepartures(), getScreenHeight());// 初始化数据适配器
        lv.setAdapter(adapter);
    }

    /**
     * 计算屏幕高度，并返回
     *
     * @return height
     */
    private int getScreenHeight() {
        Resources resources = this.getResources();
        return getResources().getDisplayMetrics().heightPixels + resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

    /**
     * 全屏显示，设置参数 View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY 滑动出现导航栏
     * View.SYSTEM_UI_FLAG_FULLSCREEN 全屏显示，不显示状态栏
     * View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 隐藏导航栏，即虚拟按键
     */
    private void fullScreenDisplay() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // 全屏显示，不显示虚拟按键
        window.setAttributes(params);
    }

    /**
     * 销毁时，做一些事情
     */
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(receiver);
        handler.removeCallbacks(runnable);// 停止定时器
        Intent intent = new Intent(this, UpdateService.class);
        stopService(intent);
        super.onDestroy();
    }

    /**
     * 捕获菜单键,返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_MENU == keyCode) {// 目录键响应
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("isArrival", false);
            startActivity(intent);
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(this, UpdateService.class);
                stopService(intent);
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void dialog(String msg, final String path) {
        isOpen = 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle("发现新版本");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isOpen = 0;
                dialog.dismiss();
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isOpen = 0;
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private class MyBroadcastReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.NETWORK_ERROR_ACTION)) {
                Toast.makeText(DeparturesActivity.this, "数据更新失败，请检查网络问题", Toast.LENGTH_SHORT).show();
                //TODO 数据更新失败有什么提示
            } else if (action.equals(Constants.SOFTWARE_UPDATE_ACTION)) {
                Bundle bundle = intent.getExtras();
                String des = bundle.getString("des");
                String path = bundle.getString("path");
                if (des != null && !des.isEmpty() && path != null && !path.isEmpty()) {
                    if (isOpen == 0) {
                        dialog(des, path);
                    }
                }
                Log.d("收到广播更新软件", "des:" + des + "path:" + path);
            }
        }
    }
}