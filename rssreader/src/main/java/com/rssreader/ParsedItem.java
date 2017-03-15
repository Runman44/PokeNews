package com.rssreader;

import org.jsoup.Jsoup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ParsedItem {
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
        this.description = html2text(description);
    }

    public void setTitle(String title) {
        this.title = html2text(title);
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPubDate(String pubDate) {
        try {
            DateFormat oldFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            DateFormat newFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

            this.pubDate = newFormat.format(oldFormat.parse(pubDate));
        } catch (ParseException e) {
            this.pubDate = pubDate;
        }
    }

    private static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
