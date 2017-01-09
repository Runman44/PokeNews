package nl.mranderson.pokefeeds.interfaces;


import com.rssreader.RssItem;

import java.util.List;

import nl.mranderson.pokefeeds.network.PokeStatus;

public interface IPokeNewsListener {

    void onDataLoaded(PokeStatus status, List<RssItem> items);

}
