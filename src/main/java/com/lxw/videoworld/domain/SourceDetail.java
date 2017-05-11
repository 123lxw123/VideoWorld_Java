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
    private String name;
    private String translateName;
    private int year;
    private String area;
    private String style;
    private String language;
    private String subtitles;
    private String releaseDate;
    private float imdbScore;
    private String imdbIntro;
    private String imdbUrl;
    private float doubanScore;
    private String doubanIntro;
    private String doubanUrl;
    private String fileFormat;
    private String fileSize;
    private String fileAmounts;
    private String fileLength;
    private String director;
    private String performer;
    private String intro;
    private String awards;
    private String episodes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(float imdbScore) {
        this.imdbScore = imdbScore;
    }

    public float getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(float doubanScore) {
        this.doubanScore = doubanScore;
    }

    public String getImdbIntro() {
        return imdbIntro;
    }

    public void setImdbIntro(String imdbIntro) {
        this.imdbIntro = imdbIntro;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public String getDoubanIntro() {
        return doubanIntro;
    }

    public void setDoubanIntro(String doubanIntro) {
        this.doubanIntro = doubanIntro;
    }

    public String getDoubanUrl() {
        return doubanUrl;
    }

    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileAmounts() {
        return fileAmounts;
    }

    public void setFileAmounts(String fileAmounts) {
        this.fileAmounts = fileAmounts;
    }

    public String getFileLength() {
        return fileLength;
    }

    public void setFileLength(String fileLength) {
        this.fileLength = fileLength;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
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
                ", name='" + name + '\'' +
                ", translateName='" + translateName + '\'' +
                ", year=" + year +
                ", area='" + area + '\'' +
                ", style='" + style + '\'' +
                ", language='" + language + '\'' +
                ", subtitles='" + subtitles + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", imdbScore='" + imdbScore + '\'' +
                ", imdbIntro='" + imdbIntro + '\'' +
                ", imdbUrl='" + imdbUrl + '\'' +
                ", doubanScore='" + doubanScore + '\'' +
                ", doubanIntro='" + doubanIntro + '\'' +
                ", doubanUrl='" + doubanUrl + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileAmounts='" + fileAmounts + '\'' +
                ", fileLength='" + fileLength + '\'' +
                ", director='" + director + '\'' +
                ", performer='" + performer + '\'' +
                ", intro='" + intro + '\'' +
                ", awards='" + awards + '\'' +
                ", episodes='" + episodes + '\'' +
                ", links='" + links + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", time=" + time +
                '}';
    }
}
