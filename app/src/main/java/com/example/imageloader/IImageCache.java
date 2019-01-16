package com.example.imageloader;

import android.graphics.Bitmap;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   : 抽象图片缓存的功能
 * </pre>
 */


public interface IImageCache {
    Bitmap get(String url);
    void put(String url , Bitmap bmp);
}
