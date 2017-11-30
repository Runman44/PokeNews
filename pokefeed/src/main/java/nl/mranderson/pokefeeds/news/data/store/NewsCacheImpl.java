package nl.mranderson.pokefeeds.news.data.store;

import java.util.List;

import nl.mranderson.pokefeeds.news.NewsItem;

public class NewsCacheImpl implements ItemCache<NewsItem> {

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean isCached() {
        return false;
    }

    @Override
    public List<NewsItem> getAll() {
        return null;
    }
}
