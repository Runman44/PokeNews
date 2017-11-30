package nl.mranderson.pokefeeds.news;

import java.util.List;

import nl.mranderson.pokefeeds.news.data.NewsRepository;

public class NewsInteractor {

    private final NewsRepository<NewsItem> repository;

    public NewsInteractor(NewsRepository<NewsItem> repository) {
        this.repository = repository;
    }

    public List<NewsItem> getNewsItems() {
        return repository.getAll();
    }
}
