package com.example.imageloader;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/16
 *     desc   :
 * </pre>
 */


public class CommonUtils {
    public static String splitUrl(String imgUrl){
        int last = imgUrl.lastIndexOf("/");
        return imgUrl.substring(last+1,imgUrl.length());
    }
}
