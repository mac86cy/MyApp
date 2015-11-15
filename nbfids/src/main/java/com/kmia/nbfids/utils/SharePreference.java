package com.kmia.nbfids.utils;

import android.content.Context;
import android.content.SharedPreferences;
/**
 *  * Copyright 2015 KMIA. All rights reserved. 
 *  *
 *  * 作者 ：mac86cy
 *  *
 *  * 邮箱 ：mac86cy@163.com
 *  *
 *  * 创建时间：2015/11/15 17:57
 *  *
 *  * 类说明：配置信息存储xml工具类
 *  
 */
public class SharePreference {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreference(Context context, String file) {
        super();
        this.sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }

    /**
     * 判断是否第一次登陆
     *
     * @return
     */
    public boolean getisFirst() {
        return sp.getBoolean("isFirst", true);
    }

    public void setisFirst(boolean isFirst) {
        editor.putBoolean("isFirst", isFirst);
        editor.commit();
    }
}
