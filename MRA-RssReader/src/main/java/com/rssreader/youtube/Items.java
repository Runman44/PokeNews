package com.rssreader.youtube;


public class Items {

    private Id id;
    private Snippet snippet;

    public Items(Id id, Snippet snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public Id getId() {
        return id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

}


