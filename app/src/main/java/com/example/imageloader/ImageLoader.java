package com.example.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   :
 * </pre>
 */


public class ImageLoader {
    //图片缓存
    IImageCache mImageCache = new MemoryCache();
    //线程池
    ExecutorService mExecutorService = Executors.newFixedThreadPool
            (Runtime.getRuntime().availableProcessors());
    Handler mUiHandler = new Handler(Looper.getMainLooper());

    private void updateImageView(final ImageView imageView, final Bitmap bmp){
        mUiHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bmp);
            }
        });
    }

    //注入缓存实现
    public void setImageCache(IImageCache imageCache){
        mImageCache = imageCache;
    }

    public void displayImage(String imageUrl , ImageView imageView){
        Bitmap bitmap = mImageCache.get(imageUrl);
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        return;
        }
        //图片没缓存，提交到线程池中下载图片
        submitLoadRequest(imageUrl , imageView);
    }
    private void submitLoadRequest(final String imageUrl , final ImageView imageView){
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if(bitmap == null){
                    return;
                }
                if(imageView.getTag().equals(imageUrl)){
                    updateImageView(imageView,bitmap);
                }
                mImageCache.put(imageUrl,bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}
