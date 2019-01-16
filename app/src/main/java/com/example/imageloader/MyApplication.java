package com.example.imageloader;

import android.app.Application;
import android.content.Context;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   :
 * </pre>
 */


public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getApplication(){
        return mContext;
    }
}
