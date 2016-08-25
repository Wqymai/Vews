package com.wyt.newsdemo.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/26.
 */
public class NewsEntity implements Serializable {
    PageBean pagebean=null;
    int ret_code=-1;

    public PageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(PageBean pagebean) {
        this.pagebean = pagebean;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }
}
