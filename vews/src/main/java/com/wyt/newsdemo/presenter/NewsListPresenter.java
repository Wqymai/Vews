package com.wyt.newsdemo.presenter;

import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import com.wyt.newsdemo.R;
import com.wyt.newsdemo.entity.NewsBody;
import com.wyt.newsdemo.entity.NewsContent;
import com.wyt.newsdemo.entity.NewsEntity;
import com.wyt.newsdemo.listener.GetNewsListListener;
import com.wyt.newsdemo.model.NewsListModel;
import com.wyt.newsdemo.view.NewsListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12418 on 2016/8/22.
 */
public class NewsListPresenter {
    private NewsListView newsListView;
    private NewsListModel newsListModel;
    private List<NewsContent> contentList;
    private int defaultPageNum = 1;


    public NewsListPresenter(NewsListView newsListView){
        this.newsListView=newsListView;
        newsListModel = new NewsListModel();
        contentList=new ArrayList<>();
    }

    public void getLatestNewsList(String channelId){
        getMoreNewsList(channelId,defaultPageNum);
    }

    public void getMoreNewsList(String channelId,final int pageNum){
            //获取新闻数据
            newsListModel.getNewsList(channelId, pageNum, new GetNewsListListener() {
                @Override
                public void success(String data) {
                    Gson gson =new Gson();
                    NewsBody newsBody = gson.fromJson(data,NewsBody.class);
                    contentList = newsBody.getNewsEntity().getPagebean().getContentlist();
                    if (pageNum == defaultPageNum){
                        newsListView.getLatestNewsList(contentList);
                    }else {
                        newsListView.getMoreNewsList(contentList);
                    }
                }

                @Override
                public void failure(String errorMessage) {

                }
            });
    }
}
