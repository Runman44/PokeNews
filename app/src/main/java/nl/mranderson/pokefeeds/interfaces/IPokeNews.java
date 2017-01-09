package nl.mranderson.pokefeeds.interfaces;

import android.app.Activity;

public interface IPokeNews {

    void getPokeNews(Activity activity, String url, IPokeNewsListener listener);

    void getPokeVideos(Activity activity, String url, IPokeNewsListener listener);
}
