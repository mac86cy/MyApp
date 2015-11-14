package com.kmia.nbfids.utils;

/**
 * Created by mac86cy on 15/11/14.
 */
public class Constants {
    public static final String SERVER_IP = "192.168.1.26";// 服务器ip
    public static final int SERVER_PORT = 8080;// 服务器端口
    public static final String URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/KMIA_NBFIDS/";// 服务器url
    public static final String ACTION = "com.kmia.message";//消息广播action
    public static final String MSGKEY = "message";//消息的key
    public static final String SAVE_USER = "saveUserInfos";//保存用户信息的xml文件名
    public static final String UPDATE_ACTION = "com.kmia.nbfids.update";//数据更新发送广播的action
    public static final String DBNAME = "nbfids.db";//数据库名称
    public static final int ROWS = 10; // 每页显示行数
    public static final int TITLEHEIGHT = 110;// 标题栏高度
    public static final int INTERVAL = 10;// 时间间隔，每页显示多少秒
    public static final int TIME_QUANTUM = 4;//显示多久的航班，单位：小时
}
