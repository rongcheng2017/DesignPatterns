package com.study.frc.designpatterns.utils;

import android.graphics.Bitmap;

/**
 *
 * Created by frc on 17-8-1.
 */

interface ImageCache {
    void put(String imageUrl, Bitmap bitmap);

    Bitmap get(String imageUrl);
}
