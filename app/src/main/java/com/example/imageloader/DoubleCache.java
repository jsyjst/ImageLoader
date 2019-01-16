package com.example.imageloader;

import android.graphics.Bitmap;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   : 双缓存
 * </pre>
 */


public class DoubleCache implements IImageCache {
    IImageCache mMemoryCache = new MemoryCache();
    IImageCache mDiskCache = new DiskCache();


    //先从内存缓存中获取照片，若没有，则从sd卡中获取
    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if(bitmap == null){
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和sd卡中
    @Override
    public void put(String url, Bitmap bmp) {
        mMemoryCache.put(url,bmp);
        mDiskCache.put(url,bmp);
    }
}
