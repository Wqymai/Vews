package com.wyt.newsdemo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class NewsContent implements Serializable {
    String channelId=null,channelName=null,desc=null,link=null,html=null,
            nid=null,pubDate=null,source=null,title=null;
    List<NewsImg> imageurls=null;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewsImg> getImageurls() {
        return imageurls;
    }

    public void setImageurls(List<NewsImg> imageurls) {
        this.imageurls = imageurls;
    }
}


