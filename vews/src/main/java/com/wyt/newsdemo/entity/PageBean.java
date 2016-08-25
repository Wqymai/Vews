package com.wyt.newsdemo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class PageBean implements Serializable {
    int allNum=-1,allPages=-1,currentPage=-1,maxResult=-1;
    List<NewsContent> contentlist=null;

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public List<NewsContent> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<NewsContent> contentlist) {
        this.contentlist = contentlist;
    }
}
