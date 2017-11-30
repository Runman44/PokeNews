package nl.mranderson.pokefeeds.network;

public class GenericItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

}
