package com.wyt.newsdemo.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.wyt.newsdemo.listener.GetNewsListListener;
import com.wyt.newsdemo.model.NewsListModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 12418 on 2016/8/22.
 */
public class OkHttpClientManager {

    private OkHttpClient okHttpClient;
    private Handler handler;

    private OkHttpClientManager(){
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpClientManager getInstance(){
        return OkHttpClientManageHolder.instance;
    }

    private static class OkHttpClientManageHolder{
        private  static final OkHttpClientManager instance =new OkHttpClientManager();
    }

    public void getAsyn(String url, final GetNewsListListener newsListListener){
        final Request request = new Request.Builder().url(url)
                .addHeader("apikey", "3eaf4cdeabefedd1d167e2c9ed7e25ca").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info","_______________"+e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data= response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        newsListListener.success(data);
                    }
                });
            }
        });
    }
}
