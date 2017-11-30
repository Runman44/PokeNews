package com.rssreader.youtube;


public class Snippet {

    private String publishedAt;
    private String title;
    private Thumbnails thumbnails;

    public Snippet(String publishedAt, String title, Thumbnails thumbnails) {
        this.publishedAt = publishedAt;
        this.title = title;
        this.thumbnails = thumbnails;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }
}
