package com.kmia.nbfids.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.kmia.nbfids.dao.AirlinesDao;
import com.kmia.nbfids.dao.ArrivalsDao;
import com.kmia.nbfids.dao.DeparturesDao;
import com.kmia.nbfids.dao.FlightStatusDao;
import com.kmia.nbfids.dao.LocationsDao;
import com.kmia.nbfids.dao.StandsDao;
import com.kmia.nbfids.model.Arrivals;
import com.kmia.nbfids.model.Departures;
import com.kmia.nbfids.model.basic.Airlines;
import com.kmia.nbfids.model.basic.FlightStatus;
import com.kmia.nbfids.model.basic.Locations;
import com.kmia.nbfids.model.basic.Stands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 *  * 类说明：数据更新请求类
 *  
 */
public class UpdateUtils {
    /**
     * 请求基础数据
     */
    public static void getBaseAll(final Context context) {
        String url = Constants.URL + "basic/all";
        final SharePreference util = new SharePreference(context, Constants.SAVE_USER);
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray arr = new JSONArray(result);
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject temp = (JSONObject) arr.get(i);
                        String tn = temp.optString("tableName");
                        switch (tn) {
                            case "airlines":
                                List<Airlines> airlineList = new ArrayList<>();
                                JSONArray airlines = temp.getJSONArray("content");
                                for (int j = 0; j < airlines.length(); j++) {
                                    JSONObject t = (JSONObject) airlines.get(j);
                                    Airlines airline = new Airlines();
                                    airline.setFid(t.optString("fid"));
                                    airline.setFiataCode(t.optString("fiataCode"));
                                    airline.setFicaoCode(t.optString("ficaoCode"));
                                    airline.setFname(t.optString("fname"));
                                    airline.setFhandlineAgent(t.optString("fhandlineAgent"));
                                    airline.setFdescription(t.optString("fdescription"));
                                    airline.setFpubDspCode(t.optString("fpubDspCode"));
                                    airlineList.add(airline);
                                }
                                AirlinesDao airlinesDao = new AirlinesDao();
                                airlinesDao.updateAirlines(airlineList);
                                break;
                            case "status":
                                List<FlightStatus> statusList = new ArrayList<>();
                                JSONArray status = temp.getJSONArray("content");
                                for (int j = 0; j < status.length(); j++) {
                                    JSONObject t = (JSONObject) status.get(j);
                                    FlightStatus flightStatus = new FlightStatus();
                                    flightStatus.setFid(t.optString("fid"));
                                    flightStatus.setFstatus(t.optString("fstatus"));
                                    flightStatus.setFaod(t.optString("faod"));
                                    flightStatus.setFpriority(t.optInt("fpriority"));
                                    flightStatus.setFdescription(t.optString("fdescription"));
                                    statusList.add(flightStatus);
                                }
                                FlightStatusDao flightStatusDao = new FlightStatusDao();
                                flightStatusDao.updateStatuss(statusList);
                                break;
                            case "locations":
                                List<Locations> locationsList = new ArrayList<>();
                                JSONArray locations = temp.getJSONArray("content");
                                for (int j = 0; j < locations.length(); j++) {
                                    JSONObject t = (JSONObject) locations.get(j);
                                    Locations location = new Locations();
                                    location.setFid(t.optString("fid"));
                                    location.setFiataCode(t.optString("fiataCode"));
                                    location.setFicaoCode(t.optString("ficaoCode"));
                                    location.setFname(t.optString("fname"));
                                    location.setFdescription(t.optString("fdescription"));
                                    location.setFabbc(t.optString("fabbc"));
                                    location.setFcountrySymbol(t.optString("fcountrySymbol"));
                                    locationsList.add(location);
                                }
                                LocationsDao locationsDao = new LocationsDao();
                                locationsDao.updateLocations(locationsList);
                                break;
                            case "stands":
                                List<Stands> standsList = new ArrayList<>();
                                JSONArray stands = temp.getJSONArray("content");
                                for (int j = 0; j < stands.length(); j++) {
                                    JSONObject t = (JSONObject) stands.get(j);
                                    Stands stand = new Stands();
                                    stand.setFid(t.optString("fid"));
                                    stand.setFstand(t.optString("fstand"));
                                    stand.setFterminal(t.optString("fterminal"));
                                    stand.setFgate(t.optString("fgate"));
                                    stand.setFdescription(t.optString("fdescription"));
                                    stand.setFtype(t.optString("ftype"));
                                    standsList.add(stand);
                                }
                                StandsDao standsDao = new StandsDao();
                                standsDao.updateStands(standsList);
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                util.setisFirst(false);// 设置第一次登陆为false
                Log.d("UPDATE-SUCCEED", "更新基础数据成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("UPDATED-FAILED", "更新基础数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 请求航班数据
     */
    @SuppressLint("SimpleDateFormat")
    public static void getTodayData(final Context context) {
        String url = Constants.URL + "arr/listAll";
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    JSONArray date = new JSONArray(result);
                    for (int i = 0; i < date.length(); i++) {
                        JSONObject temp = (JSONObject) date.get(i);
                        String tn = temp.optString("tableName");
                        if (tn.equals("arrivals")) {
                            List<Arrivals> arrivals = new ArrayList<>();
                            JSONArray dataList = temp.getJSONArray("content");
                            for (int j = 0; j < dataList.length(); j++) {
                                JSONObject t = (JSONObject) dataList.get(j);
                                if (!t.optString("fid").equals("")) {
                                    Date stpDate = null;// 前站计划离港
                                    Date abpDate = null;// 前站实际离港
                                    Date staDate = null;// 本站计划进港
                                    Date tdtDate = null;// 本站实际进港
                                    Date sdtDate = null;// 运营日
                                    try {
                                        // 进港时间转换
                                        if (!t.optString("fstp").equals("")) {
                                            stpDate = format.parse(t.optString("fstp"));
                                        }
                                        if (!t.optString("fsta").equals("")) {
                                            staDate = format.parse(t.optString("fsta"));
                                        }
                                        if (!t.optString("fabp").equals("")) {
                                            abpDate = format.parse(t.optString("fabp"));
                                        }
                                        if (!t.optString("ftdt").equals("")) {
                                            tdtDate = format.parse(t.optString("ftdt"));
                                        }
                                        if (!t.optString("fsdt").equals("")) {
                                            sdtDate = format.parse(t.optString("fsdt"));
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Arrivals flight = new Arrivals();
                                    flight.setFid(t.optString("fid"));
                                    flight.setFfln(t.optString("ffln"));
                                    flight.setForg(t.optString("forg"));
                                    flight.setFdes(t.optString("fdes"));
                                    flight.setFstp(stpDate);
                                    flight.setFabp(abpDate);
                                    flight.setFsta(staDate);
                                    flight.setFtdt(tdtDate);
                                    flight.setFfsa(t.optString("ffsa"));
                                    flight.setFgvf(t.optString("fgvf"));
                                    flight.setFcan(t.optString("fcan"));
                                    flight.setFtys(t.optString("ftys"));
                                    flight.setFreg(t.optString("freg"));
                                    flight.setFtar(t.optString("ftar"));
                                    flight.setFgat(t.optString("fgat"));
                                    flight.setFgt2(t.optString("fgt2"));
                                    flight.setFcar(t.optString("fcar"));
                                    flight.setFflc(t.optString("fflc"));
                                    flight.setFcsf(t.optString("fcsf"));
                                    flight.setFmff(t.optString("fmff"));
                                    flight.setFflt(t.optString("fflt"));
                                    flight.setFflx(t.optString("fflx"));
                                    flight.setFct1(t.optString("fct1"));
                                    flight.setFct2(t.optString("fct2"));
                                    flight.setFcla(t.optString("fcla"));
                                    flight.setFfst(t.optString("ffst"));
                                    flight.setFnat(t.optString("fnat"));
                                    flight.setFtof(t.optString("ftof"));
                                    flight.setFsdt(sdtDate);
                                    arrivals.add(flight);
                                }
                            }
                            ArrivalsDao arrivalsDao = new ArrivalsDao();
                            arrivalsDao.updateArrivals(arrivals);
                        } else if (tn.equals("departures")) {
                            List<Departures> departures = new ArrayList<>();
                            JSONArray dataList = temp.getJSONArray("content");
                            for (int j = 0; j < dataList.length(); j++) {
                                JSONObject t = (JSONObject) dataList.get(j);
                                if (!t.optString("fid").equals("")) {
                                    Date stdDate = null;// 本站计划离港
                                    Date abtDate = null;// 本站实际离港
                                    Date stnDate = null;// 下站计划进港
                                    Date aanDate = null;// 下站实际进港
                                    Date sdtDate = null;// 运营日
                                    try {
                                        // 出港时间转换
                                        if (!t.optString("fstd").equals("")) {
                                            stdDate = format.parse(t.optString("fstd"));
                                        }
                                        if (!t.optString("fabt").equals("")) {
                                            abtDate = format.parse(t.optString("fabt"));
                                        }
                                        if (!t.optString("fstn").equals("")) {
                                            stnDate = format.parse(t.optString("fstn"));
                                        }
                                        if (!t.optString("faan").equals("")) {
                                            aanDate = format.parse(t.optString("faan"));
                                        }
                                        if (!t.optString("fsdt").equals("")) {
                                            sdtDate = format.parse(t.optString("fsdt"));
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Departures flight = new Departures();
                                    flight.setFid(t.optString("fid"));
                                    flight.setFfln(t.optString("ffln"));
                                    flight.setForg(t.optString("forg"));
                                    flight.setFdes(t.optString("fdes"));
                                    flight.setFstd(stdDate);
                                    flight.setFabt(abtDate);
                                    flight.setFstn(stnDate);
                                    flight.setFaan(aanDate);
                                    flight.setFfsa(t.optString("ffsa"));
                                    flight.setFgvf(t.optString("fgvf"));
                                    flight.setFcan(t.optString("fcan"));
                                    flight.setFtys(t.optString("ftys"));
                                    flight.setFreg(t.optString("freg"));
                                    flight.setFtar(t.optString("ftar"));
                                    flight.setFgat(t.optString("fgat"));
                                    flight.setFgt2(t.optString("fgt2"));
                                    flight.setFcir(t.optString("fcir"));
                                    flight.setFflc(t.optString("fflc"));
                                    flight.setFcsf(t.optString("fcsf"));
                                    flight.setFmff(t.optString("fmff"));
                                    flight.setFflt(t.optString("fflt"));
                                    flight.setFflx(t.optString("fflx"));
                                    flight.setFct1(t.optString("fct1"));
                                    flight.setFct2(t.optString("fct2"));
                                    flight.setFcla(t.optString("fcla"));
                                    flight.setFfst(t.optString("ffst"));
                                    flight.setFnat(t.optString("fnat"));
                                    flight.setFtof(t.optString("ftof"));
                                    flight.setFsdt(sdtDate);
                                    departures.add(flight);
                                }
                            }
                            DeparturesDao departuresDao = new DeparturesDao();
                            departuresDao.updateDepartures(departures);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("UPDATE-SUCCEED","更新航班数据成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Intent intent = new Intent();
                intent.setAction(Constants.ACTION);
                context.sendBroadcast(intent);
                Log.e("UPDATE-FAILED", "更新航班数据失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
}
