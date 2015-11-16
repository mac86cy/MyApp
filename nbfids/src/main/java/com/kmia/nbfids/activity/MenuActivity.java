package com.kmia.nbfids.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.kmia.nbfids.R;
import com.kmia.nbfids.utils.UpdateUtils;
import com.kmia.nbfids.view.MyImageButton;

/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：menu菜单，用于显示，和响应遥控器menu按键
 *  
 */
public class MenuActivity extends Activity {

    private MyImageButton updateBase;
    private MyImageButton updateFlights;
    private MyImageButton changePage;
    private boolean isArrival;
    // 自定义ImageButton上面显示的字体的颜色
    private int BTN_TEXTCOLOR = Color.rgb(189, 104, 221);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreenDisplay();// 全屏显示
        setContentView(R.layout.menubutton);
        initUi();

        Intent intent = getIntent();
        isArrival = intent.getBooleanExtra("isArrival", true);

        updateBase.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUtils.getBaseAll(MenuActivity.this);// 更新基础数据
                Toast.makeText(MenuActivity.this, "基础数据更新中", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        updateFlights.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateUtils.getTodayData(MenuActivity.this);// 更新航班数据
                Toast.makeText(MenuActivity.this, "航班数据更新中", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        changePage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (isArrival) {
                    i = new Intent(MenuActivity.this, DeparturesActivity.class);
                    ArrivalsActivity.instance.finish();
                } else {
                    i = new Intent(MenuActivity.this, ArrivalsActivity.class);
                    DeparturesActivity.instance.finish();
                }
                startActivity(i);
                finish();
            }
        });
    }


    private void initUi() {
        updateBase = (MyImageButton) findViewById(R.id.update_base);
        updateFlights = (MyImageButton) findViewById(R.id.update_flights);
        changePage = (MyImageButton) findViewById(R.id.change_page);
        LinearLayout l1 = (LinearLayout) findViewById(R.id.mb1);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.mb2);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.mb3);
        LayoutParams baseLp = (LayoutParams) updateBase.getLayoutParams();
        LayoutParams filghtLp = (LayoutParams) updateFlights.getLayoutParams();
        LayoutParams changeLp = (LayoutParams) changePage.getLayoutParams();
        baseLp.height = getScreenHeight() / 9;
        baseLp.width = getScreenWidth() / 3;
        filghtLp.height = getScreenHeight() / 9;
        filghtLp.width = getScreenWidth() / 3;
        changeLp.height = getScreenHeight() / 9;
        changeLp.width = getScreenWidth() / 3;
        baseLp.bottomMargin = 10;
        filghtLp.bottomMargin = 10;
        changeLp.bottomMargin = 10;
        l1.setLayoutParams(baseLp);
        l2.setLayoutParams(filghtLp);
        l3.setLayoutParams(changeLp);
        // 显示文字
        updateBase.setText("更新基础数据");
        updateFlights.setText("更新航班数据");
        if (isArrival) {
            changePage.setText("切换出港页面");
        } else {
            changePage.setText("切换进港页面");
        }
        // 显示文字大小
        float BTN_TEXTSIZE = 50f;
        updateBase.setTextSize(BTN_TEXTSIZE);
        updateFlights.setTextSize(BTN_TEXTSIZE);
        changePage.setTextSize(BTN_TEXTSIZE);
        // 显示文字的颜色
        updateBase.setColor(BTN_TEXTCOLOR);
        updateFlights.setColor(BTN_TEXTCOLOR);
        changePage.setColor(BTN_TEXTCOLOR);
    }

    private void fullScreenDisplay() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // 全屏显示，不显示虚拟按键
        window.setAttributes(params);
    }

    private int getScreenHeight() {
        int height = getResources().getDisplayMetrics().heightPixels;
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int barHeight = resources.getDimensionPixelSize(resourceId);// 获取NavigationBar的高度
        height += barHeight;// 获取全部屏幕高度，导航栏+标题栏+状态栏
        return height;
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }
}
