package nl.mranderson.pokefeeds.news;

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

    String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    String getDate() {
        return date;
    }
}
