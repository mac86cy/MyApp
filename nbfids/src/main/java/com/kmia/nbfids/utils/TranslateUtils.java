package com.kmia.nbfids.utils;

import com.kmia.nbfids.R;

/**
 * Created by mac86cy on 15/11/14.
 */
public class TranslateUtils {
    public static int airlineImage(String name) {
        switch (name) {
            case "3U":
                return R.drawable.airline_3u;
            case "8L":
                return R.drawable.airline_8l;
            case "9C":
                return R.drawable.airline_9c;
            case "CA":
                return R.drawable.airline_ca;
            case "CZ":
                return R.drawable.airline_cz;
            case "HU":
                return R.drawable.airline_hu;
            case "KA":
                return R.drawable.airline_ka;
            case "KY":
                return R.drawable.airline_ky;
            case "MF":
                return R.drawable.airline_mf;
            case "MU":
                return R.drawable.airline_mu;
            case "SC":
                return R.drawable.airline_sc;
            case "ZH":
                return R.drawable.airline_zh;
            default:
                return R.mipmap.ic_launcher;
        }
    }

    public static String locationName(String name) {
        switch (name) {
            case "Guangzhou/Baiyun":
                return "Guangzhou";
            case "Chengdu/Shuangliu":
                return "Chengdu";
            case "Beijing/Nanyuan":
                return "Nanyuan";
            case "Shanhaiguan/Qinhuang":
                return "QinhuangDao";
            case "Changsha/Huanghua":
                return "Changsha";
            case "Huangshan/Tunxi":
                return "Huangshan";
            case "普洱/思茅":
                return "普洱";
            default:
                return name;
        }
    }

    public static String statusName(String name, int arrOrDep, int lang) {
        if (lang != 1) {// 中文
            if (arrOrDep != 1) {// 进港
                switch (name) {
                    case "上/下轮档":
                        return "上轮档";

                    default:
                        return name;
                }
            } else {
                switch (name) {
                    case "上/下轮档":
                        return "下轮档";

                    default:
                        return name;
                }
            }
        } else {// 英文
            if (arrOrDep != 1) {// 进港
                switch (name) {
                    case "上/下轮档":
                        return "Up wheel";
                    case "预计":
                        return "Estimate";
                    case "计划":
                        return "Schedule";
                    case "正常":
                        return "Ordinary";
                    case "行李提取开始":
                        return "Baggaging";
                    case "改降":
                        return "Change landing";
                    case "最后进近":
                        return "Final approach";
                    case "时间待定":
                        return "Time to be determined";
                    case "行李提取结束":
                        return "Baggaged";
                    case "着陆":
                        return "Landing";
                    case "取消":
                        return "Canceled";
                    case "前站起飞":
                        return "Previous Station Departured";
                    default:
                        return name;
                }
            } else {
                switch (name) {
                    case "滑回":
                        return "Slide back";
                    case "正在登机":
                        return "Boarding";
                    case "登机准备":
                        return "Boarding ready";
                    case "登机开放":
                        return "Open boarding";
                    case "催促登机":
                        return "Last call";
                    case "登机结束":
                        return "End boarding";
                    case "本站起飞":
                        return "Takeoff";
                    case "取消":
                        return "Canceled";
                    case "时间待定":
                        return "Time to be determined";
                    case "正常":
                        return "Ordinary";
                    case "计划":
                        return "Schedule";
                    case "预计":
                        return "Estimate";
                    case "上/下轮档":
                        return "Down wheel";

                    default:
                        return name;
                }
            }

        }
    }
}
