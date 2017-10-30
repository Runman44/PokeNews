package nl.mranderson.pokefeeds.news.data;


import java.util.List;

public interface NewsRepository<T> {

    List<T> getAll();

}
