package com.lxw.videoworld.domain;

import java.io.Serializable;

/**
 * Created by Zion on 2017/6/14.
 */
public class SearchResult implements Serializable{
    private String ciliLink;
    private String thunderLink;
    private String amounts;
    private String title;
    private String date;
    private String size;
    private String hot;
    private String type;
    private String time;

    public String getCiliLink() {
        return ciliLink;
    }

    public void setCiliLink(String ciliLink) {
        this.ciliLink = ciliLink;
    }

    public String getThunderLink() {
        return thunderLink;
    }

    public void setThunderLink(String thunderLink) {
        this.thunderLink = thunderLink;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
