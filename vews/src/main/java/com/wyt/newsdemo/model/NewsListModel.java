package com.wyt.newsdemo.model;

import com.wyt.newsdemo.listener.GetNewsListListener;
import com.wyt.newsdemo.utils.OkHttpClientManager;

/**
 * Created by 12418 on 2016/8/22.
 */
public class NewsListModel {

    public NewsListModel(){

    }

    public void getNewsList(String channelId, int pageNum, GetNewsListListener newsListListener){
        String url="http://apis.baidu.com/showapi_open_bus/channel_news/search_news?" +
                "channelId=" +channelId+ "&page="+pageNum;
        //OkHttp网络工具类
        OkHttpClientManager.getInstance().getAsyn(url,newsListListener);
    }
}
