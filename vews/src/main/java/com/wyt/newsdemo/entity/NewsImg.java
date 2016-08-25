package com.wyt.newsdemo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/26.
 */
public class NewsImg implements Serializable {
    int height=-1,width=-1;
    String url=null;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
