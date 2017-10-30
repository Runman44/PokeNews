package nl.mranderson.pokefeeds.news.data.store;


import java.util.List;

import nl.mranderson.pokefeeds.news.NewsItem;

public interface NewsCache {

    boolean isExpired();

    boolean isCached();

    List<NewsItem> getAll();
}
