package nl.mranderson.pokefeeds.video;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;

public interface VideoInteractor {
    void getVideos(String url, DataLoadedListener listener);
}

