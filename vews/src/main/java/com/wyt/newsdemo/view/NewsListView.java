package com.wyt.newsdemo.view;

import com.wyt.newsdemo.entity.NewsContent;

import java.util.List;

/**
 * Created by 12418 on 2016/8/22.
 */
public interface NewsListView {

    // 初始化成员变量
    public void initVariable();

    //初始化界面
    public void initView();

    //获取最新新闻列表
    public void getLatestNewsList(List<NewsContent> contentList);

     // 获取更多新闻列表
    public void getMoreNewsList(List<NewsContent> contentList);

    //进入新闻详情页
    public void toNewsPage(int position);

     //提示消息
    public void showMessage(String message);

}
