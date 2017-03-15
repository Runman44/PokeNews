package nl.mranderson.pokefeeds.news;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;

public interface NewsInteractor {
    void getNews(String url, DataLoadedListener listener);
}

