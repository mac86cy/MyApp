package com.kmia.nbfids.utils;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：全局静态变量
 *  
 */
public class Constants {
    public static final String SERVER_IP = "10.2.18.86";// 服务器ip
    public static final int SERVER_PORT = 8080;// 服务器端口
    public static final String URL = "http://" + SERVER_IP + ":" + SERVER_PORT + "/KMIA_NBFIDS/";// 服务器url
    public static final String ACTION = "com.kmia.message.error";//消息广播action
    public static final String SAVE_USER = "saveUserInfos";//保存用户信息的xml文件名
    public static final String DBNAME = "nbfids.db";//数据库名称
    public static final int ROWS = 10; // 每页显示行数
    public static final int INTERVAL = 10;// 时间间隔，每页显示多少秒
    public static final int TIME_QUANTUM = 4;//显示多久的航班，单位：小时
}
