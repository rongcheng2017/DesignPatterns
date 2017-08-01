package com.study.frc.designpatterns.utils;

import android.graphics.Bitmap;

/**
 * 双重缓存
 * Created by frc on 17-8-1.
 */

public class DoubleCache implements ImageCache {
    private DiskCache diskCache = new DiskCache();
    private MemoryCache memoryCache = new MemoryCache();

    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        memoryCache.put(imageUrl, bitmap);
        diskCache.put(imageUrl, bitmap);
    }

    @Override
    public Bitmap get(String imageUrl) {
        Bitmap bitmap = memoryCache.get(imageUrl);
        if (bitmap == null) {
            bitmap = diskCache.get(imageUrl);
        }
        return bitmap;
    }
}
