package com.wyt.newsdemo.listener;

import com.wyt.newsdemo.entity.NewsContent;

import java.util.List;

/**
 * Created by 12418 on 2016/8/22.
 */
public interface GetNewsListListener {
    /**
     * 获取成功
     */
    public void success(String data);

    /**
     * 获取失败
     */
    public void failure(String errorMessage);
}
