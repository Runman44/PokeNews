package com.rssreader;


public class Snippet {
    private String publishedAt;

    private String title;

    private Thumbnails thumbnails;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
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

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return "ClassPojo [publishedAt = " + publishedAt + ", title = " + title + ", thumbnails = " + thumbnails + "]";
    }
}
