package nl.mranderson.pokefeeds.news.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    private String title;
    private String description;
    private String link;
    @SerializedName("pubDate")
    private String date;

    public NewsItem(String title, String description, String link, String date) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }
}
