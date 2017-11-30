package nl.mranderson.pokefeeds.news.data.store;


import java.util.List;

public interface ItemDataStore<T> {

    List<T> get();

}

