package nl.mranderson.pokefeeds.news.data.store;

import java.util.List;

import nl.mranderson.pokefeeds.news.NewsItem;
import nl.mranderson.pokefeeds.news.data.NewsCache;

public class DiskUserDataStore implements NewsDataStore {

    private NewsCache newsCache;

    public DiskUserDataStore(NewsCache newsCache) {
        this.newsCache = newsCache;
    }

    @Override
    public List<NewsItem> get() {
        return newsCache.getAll();
    }
}

