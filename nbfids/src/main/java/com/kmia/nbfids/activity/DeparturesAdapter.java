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
import com.kmia.nbfids.model.Departures;
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
 *  * 类说明：离港航班页面数据适配器
 *  
 */
public class DeparturesAdapter extends BaseAdapter {
    private static int CN_OR_EN = 0; // 中英文标志位
    private LayoutInflater mInflater;
    private List<Departures> list = new ArrayList<>();
    private LocationsDao locationsDao;// 地名数据源
    private FlightStatusDao statusDao;// 状态数据源
    private int screenHeight;// 屏幕高度

    public DeparturesAdapter(Context context, List<Departures> list, int height) {
        super();
        this.list = list;
        this.screenHeight = height;
        locationsDao = new LocationsDao();
        statusDao = new FlightStatusDao();
        mInflater = LayoutInflater.from(context);
    }

    public void update(List<Departures> list, int language) {
        CN_OR_EN = language;
        this.list = list;
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
        String desName = "";
        String statusName = "";
        String fdes = list.get(position).getFdes();
        String ffsa = list.get(position).getFfsa();
        convertView = mInflater.inflate(R.layout.departure_item, null); // 设置view
        ImageView iv = (ImageView) convertView.findViewById(R.id.dimg);// 航空公司logo
        TextView fln = (TextView) convertView.findViewById(R.id.dflt);// 找到航班号
        TextView des = (TextView) convertView.findViewById(R.id.des);// 找到目的地
        TextView std = (TextView) convertView.findViewById(R.id.std);// 计划出发
        TextView abt = (TextView) convertView.findViewById(R.id.abt);// 实际出发
        TextView stn = (TextView) convertView.findViewById(R.id.stn);// 下站计划到港
        TextView aan = (TextView) convertView.findViewById(R.id.aan);// 下站实际到港
        TextView status = (TextView) convertView.findViewById(R.id.dstatus);// 状态

        FlightStatus fstatus = statusDao.getStatusByCode(ffsa);
        Locations location = locationsDao.getLocationByIataCode(fdes);

        if (CN_OR_EN != 1) {// 中文
            if (location != null) {
                desName = location.getFdescription();
            }
            if (fstatus != null) {
                statusName = fstatus.getFdescription();
            }
        } else {// 英文
            if (location != null) {
                desName = location.getFname();
            }
            if (fstatus != null) {
                statusName = fstatus.getFdescription();
            }
        }

        iv.setImageResource(TranslateUtils.airlineImage(list.get(position).getFflc()));
        fln.setText(list.get(position).getFflt());
        des.setText(TranslateUtils.locationName(desName));
        std.setText(DateFormat.getDate(list.get(position).getFstd()));
        abt.setText(DateFormat.getDate(list.get(position).getFabt()));
        stn.setText(DateFormat.getDate(list.get(position).getFstn()));
        aan.setText(DateFormat.getDate(list.get(position).getFaan()));
        status.setText(TranslateUtils.statusName(statusName, 1, CN_OR_EN));
        if (ffsa.equals("CX")) {// 取消状态红色显示
            status.setTextColor(Color.RED);
        }
        des.setSelected(true);
        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.depItem);// 找到item
        LayoutParams linearParams = (LayoutParams) ll.getLayoutParams();
        linearParams.height = screenHeight * 6 / (Constants.ROWS * 7);// 每页显示9个航班，根据高度计算每个item的高度
        ll.setLayoutParams(linearParams);// 设置item的高度参数

        return convertView;
    }
}