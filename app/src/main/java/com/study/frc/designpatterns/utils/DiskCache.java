package com.study.frc.designpatterns.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SD卡缓存
 * Created by frc on 17-8-1.
 */

public class DiskCache implements ImageCache {
    static String cacheDir = "sdcard/cache/";

    @Override
    public void put(String imageUrl, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(cacheDir + imageUrl);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Bitmap get(String imageUrl) {
        return BitmapFactory.decodeFile(cacheDir + imageUrl);
    }
}
