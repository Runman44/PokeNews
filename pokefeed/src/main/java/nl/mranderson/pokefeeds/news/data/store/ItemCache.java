package nl.mranderson.pokefeeds.news.data.store;


import java.util.List;

public interface ItemCache<T> {

    boolean isExpired();

    boolean isCached();

    List<T> getAll();
}
