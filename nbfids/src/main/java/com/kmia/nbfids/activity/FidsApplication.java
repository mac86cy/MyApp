package com.kmia.nbfids.activity;

import android.app.Application;

import org.xutils.x;

/**
 * Created by mac86cy on 15/11/14.
 */
public class FidsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
