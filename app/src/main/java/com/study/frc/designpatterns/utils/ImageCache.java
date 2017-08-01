package com.study.frc.designpatterns.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by frc on 17-8-1.
 */

public class ImageCache {
    //图片缓存
    private LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        mImageCache = initImageCache();
    }

    private LruCache<String, Bitmap> initImageCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        return new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String imageUrl) {
        return mImageCache.get(imageUrl);
    }
}
