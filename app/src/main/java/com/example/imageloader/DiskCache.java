package com.example.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   : sd卡缓存类
 * </pre>
 */


public class DiskCache implements IImageCache {
    private static final String TAG = "DiskCache";
    private static String cacheDir  = MyApplication.getApplication().getExternalFilesDir("image").getAbsolutePath();

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, "get: "+cacheDir+"/"+CommonUtils.splitUrl(url));
        return BitmapFactory.decodeFile(cacheDir +"/"+ CommonUtils.splitUrl(url));
    }

    @Override
    public void put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream = null;
        File file = new File(cacheDir);
        if(!file.exists()){
            file.mkdirs();
        }
        try{
            File imgFile = new File(cacheDir,CommonUtils.splitUrl(url));
            fileOutputStream = new FileOutputStream(imgFile);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } finally  {
            if(fileOutputStream != null){
                try {
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
