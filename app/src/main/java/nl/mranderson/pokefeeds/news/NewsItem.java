package nl.mranderson.pokefeeds.news;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    private String title;
    private String description;
    private String link;
    @SerializedName("pubDate")
    private String date;

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

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
