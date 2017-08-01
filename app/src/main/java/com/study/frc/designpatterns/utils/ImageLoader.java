package com.study.frc.designpatterns.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by frc on 17-8-1.
 */

public class ImageLoader {
    private ImageCache mImageCache = new ImageCache();
    //线程池，线程数为cpu数
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    private Bitmap downloadBitmap(String pathUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(pathUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void displayImage(final String url, final ImageView imageView) {
        Bitmap bitmap= mImageCache.get(url);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadBitmap(url);
                if (bitmap == null)
                    return;
                if (imageView.getTag().equals(url))
                    imageView.setImageBitmap(bitmap);
                mImageCache.put(url, bitmap);
            }
        });
    }
}
