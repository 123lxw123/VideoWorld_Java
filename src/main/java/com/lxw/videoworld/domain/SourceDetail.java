package com.lxw.videoworld.domain;

import java.io.Serializable;

/**
 * Created by lxw9047 on 2017/5/3.
 */
public class SourceDetail implements Serializable {

    private String url;
    private String id;
    private String category;
    private String type;
    private String title;
    private String images;
    private String content;
    private String links;
    private int date;
    private String status;
    private long time;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SourceDetail{" +
                "url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", images='" + images + '\'' +
                ", content='" + content + '\'' +
                ", links='" + links + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}
