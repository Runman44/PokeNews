package com.rssreader;


public class Items {
    private Id id;

    private Snippet snippet;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", snippet = " + snippet + "]";
    }
}


