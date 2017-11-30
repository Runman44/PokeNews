package nl.mranderson.pokefeeds.news.data.store;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rssreader.RssReader;
import com.rssreader.RssReaderImpl;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import nl.mranderson.pokefeeds.news.NewsItem;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CloudUserDataStore implements ItemDataStore {

    @Inject
    OkHttpClient okHttpClient;

    //TODO inject GSON and RssReader OkHttpClient
    @Override
    public List<NewsItem> get() {
        List<NewsItem> newsItems;
        RssReader rssReader = new RssReaderImpl();
        Gson gson = new Gson();
        try {
            String rawJson = run();
            JSONArray newsJsonItems = rssReader.getFromRssFeed(rawJson);
            newsItems = new ArrayList<>(gson.fromJson(newsJsonItems.toString(), new TypeToken<Collection<NewsItem>>() {}.getType()));
        } catch (Exception ex) {
            newsItems = null;
        }
        return newsItems;
    }

    //TODO do a MRA-HTTP module. That does is outside the main thread ofc.
    private String run() throws IOException {
        final String contentUrl = "https://bulbanews.bulbagarden.net/feed/news.rss";

        Request request = new Request.Builder()
                .url(contentUrl)
                .build();
        okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}