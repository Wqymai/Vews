package com.wyt.newsdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 12418 on 2016/3/28.
 */

public class ImageLoader {
    ImageView imageView;
    String url;

   private Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           if (imageView.getTag().equals(url)) {
               imageView.setImageBitmap((Bitmap) msg.obj);
           }
       }
   };

    public void showBitmap(ImageView imageView, final String url) {
        this.imageView = imageView;
        this.url=url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmap(url);
                Message message=Message.obtain();
                message.obj=bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }

    public Bitmap getBitmap(String urlString) {
        Bitmap bitmap;
        InputStream inputStream = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
            httpURLConnection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

}
