package nl.mranderson.pokefeeds.network;


import com.rssreader.RssItem;

import java.util.List;

public class PokeNewsResponse {

    private List<RssItem> items;
    private Exception exception;

    public List<RssItem> getItems() {
        return items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }

    public void setException(final Exception exception) {
        this.exception = exception;
    }

    public boolean hasException() {
        return exception != null;
    }

}