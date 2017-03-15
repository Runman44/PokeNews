package nl.mranderson.pokefeeds.interfaces;


import java.util.List;

import nl.mranderson.pokefeeds.network.GenericItem;
import nl.mranderson.pokefeeds.network.GenericStatus;

public interface DataLoadedListener {

    void onDataLoaded(GenericStatus status, List<GenericItem> items);

}
