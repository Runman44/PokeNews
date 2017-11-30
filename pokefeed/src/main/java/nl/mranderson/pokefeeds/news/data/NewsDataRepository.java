package nl.mranderson.pokefeeds.news.data;

import java.util.List;

import nl.mranderson.pokefeeds.news.NewsItem;
import nl.mranderson.pokefeeds.news.data.store.NewsCacheImpl;
import nl.mranderson.pokefeeds.news.data.store.ItemDataStore;

public class NewsDataRepository implements NewsRepository<NewsItem> {

    private static NewsDataRepository newsDataRepository;
    private NewsDataStoreFactory newsDataStoreFactory;

    private NewsDataRepository(NewsDataStoreFactory newsDataStoreFactory) {
        this.newsDataStoreFactory = newsDataStoreFactory;
    }

    public static NewsRepository<NewsItem> getInstance() {
        if (newsDataRepository == null) {
            newsDataRepository = new NewsDataRepository(new NewsDataStoreFactory(new NewsCacheImpl()));
        }
        return newsDataRepository;
    }

    @Override
    public List<NewsItem> getAll() {
        ItemDataStore itemDataStore = newsDataStoreFactory.create();
        return itemDataStore.get();
    }


}
