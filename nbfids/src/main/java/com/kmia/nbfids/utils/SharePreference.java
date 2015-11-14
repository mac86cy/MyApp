package com.kmia.nbfids.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mac86cy on 15/11/14.
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
