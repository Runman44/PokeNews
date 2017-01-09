package nl.mranderson.pokefeeds.network;

import android.content.Context;

import com.rssreader.RssItem;
import com.rssreader.RssReader;

import java.util.List;


public class PokeNewsLoader extends android.content.AsyncTaskLoader<PokeNewsResponse> {

    private final String url;

    public PokeNewsLoader(final Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public PokeNewsResponse loadInBackground() {

        PokeNewsResponse response = new PokeNewsResponse();

        try {
            RssReader rssReader = new RssReader(url);
            final List<RssItem> items = rssReader.getItems();
            response.setItems(items);
        } catch (Exception ex) {
            response.setException(ex);
        }
        return response;
    }
}
