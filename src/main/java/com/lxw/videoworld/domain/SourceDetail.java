package com.lxw.videoworld.domain;

import java.io.Serializable;

/**
 * Created by lxw9047 on 2017/5/3.
 */
public class SourceDetail implements Serializable {

    private String url;
    private String title;
    private String images;
    private String content;
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

    @Override
    public String toString() {
        return "SourceDetail{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", images='" + images + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}
