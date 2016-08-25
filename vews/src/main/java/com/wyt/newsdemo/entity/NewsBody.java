package com.wyt.newsdemo.entity;

/**
 * Created by 12418 on 2016/8/22.
 */
public class NewsBody {
    int showapi_res_code;
    String showapi_res_error;
    NewsEntity showapi_res_body=null;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public NewsEntity getNewsEntity() {
        return showapi_res_body;
    }

    public void setNewsEntity(NewsEntity newsEntity) {
        this.showapi_res_body = newsEntity;
    }
}
