package com.kmia.nbfids.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.kmia.nbfids.R;
import com.kmia.nbfids.dao.FlightStatusDao;
import com.kmia.nbfids.dao.LocationsDao;
import com.kmia.nbfids.model.Arrivals;
import com.kmia.nbfids.model.basic.FlightStatus;
import com.kmia.nbfids.model.basic.Locations;
import com.kmia.nbfids.utils.Constants;
import com.kmia.nbfids.utils.DateFormat;
import com.kmia.nbfids.utils.TranslateUtils;

import java.util.ArrayList;
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
 *  * 类说明：到港航班页面数据适配器
 *  
 */
public class ArrivalsAdapter extends BaseAdapter {
    private static int CN_OR_EN = 0; // 中英文标志位
    private LayoutInflater mInflater;
    private List<Arrivals> list = new ArrayList<>();
    private LocationsDao locationsDao;// 地名数据源
    private FlightStatusDao statusDao;// 状态数据源
    private int screenHeight; // 屏幕高度

    public ArrivalsAdapter(Context context, List<Arrivals> list, int height) {
        super();
        if (list != null) {
            this.list = list;
        }
        locationsDao = new LocationsDao();
        statusDao = new FlightStatusDao();
        mInflater = LayoutInflater.from(context);
        this.screenHeight = height;
    }

    public void update(List<Arrivals> list, int language) {
        CN_OR_EN = language;
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String orgName = "";
        String statusName = "";
        String forg = list.get(position).getForg();
        String ffsa = list.get(position).getFfsa();
        convertView = mInflater.inflate(R.layout.arrival_item, null);// 设置view
        ImageView iv = (ImageView) convertView.findViewById(R.id.aimg);// 航空公司logo
        TextView fln = (TextView) convertView.findViewById(R.id.aflt);// 找到航班号
        TextView org = (TextView) convertView.findViewById(R.id.org);// 找到始发地
        TextView sta = (TextView) convertView.findViewById(R.id.sta);// 计划到达
        TextView tdt = (TextView) convertView.findViewById(R.id.tdt);// 实际到达
        TextView stp = (TextView) convertView.findViewById(R.id.stp);// 前站计划
        TextView abp = (TextView) convertView.findViewById(R.id.abp);// 前站实际
        TextView status = (TextView) convertView.findViewById(R.id.astatus);// 状态

        FlightStatus fstatus = statusDao.getStatusByCode(ffsa);
        Locations location = locationsDao.getLocationByIataCode(forg);

        if (CN_OR_EN != 1) {// 中文
            if (location != null) {
                orgName = location.getFdescription();
            }
            if (fstatus != null) {
                statusName = fstatus.getFdescription();
            }
        } else {// 英文
            if (location != null) {
                orgName = location.getFname();
            }
            if (fstatus != null) {
                statusName = fstatus.getFdescription();
            }
        }

        iv.setImageResource(TranslateUtils.airlineImage(list.get(position).getFflc()));
        fln.setText(list.get(position).getFflt());
        org.setText(TranslateUtils.locationName(orgName));
        sta.setText(DateFormat.getDate(list.get(position).getFsta()));
        tdt.setText(DateFormat.getDate(list.get(position).getFtdt()));
        stp.setText(DateFormat.getDate(list.get(position).getFstp()));
        abp.setText(DateFormat.getDate(list.get(position).getFabp()));
        status.setText(TranslateUtils.statusName(statusName, 0, CN_OR_EN));
        if (ffsa.equals("CX")) {// 取消状态红色显示
            status.setTextColor(Color.RED);
        }
        org.setSelected(true);
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.arrItem);// 找到item
        LayoutParams linearParams = (LayoutParams) ll.getLayoutParams();
        linearParams.height = screenHeight * 6 / (Constants.ROWS * 7);// 每页显示10个航班，根据高度计算每个item的高度
        ll.setLayoutParams(linearParams);// 设置item的高度参数

        return convertView;
    }
}