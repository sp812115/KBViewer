package com.koubou.kbviewer.entity;

public class Video {
    String title;
    String cover;
    String url;
    String description;

    public Video(String title, String cover, String url, String description) {
        this.title = title;
        this.cover = cover;
        this.url = url;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getCover() {
        return cover;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
