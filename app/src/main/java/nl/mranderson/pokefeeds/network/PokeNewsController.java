package nl.mranderson.pokefeeds.network;

import android.app.Activity;
import android.os.Bundle;

import nl.mranderson.pokefeeds.interfaces.IPokeNews;
import nl.mranderson.pokefeeds.interfaces.IPokeNewsListener;


public class PokeNewsController implements IPokeNews, android.app.LoaderManager.LoaderCallbacks<PokeNewsResponse> {

    private PokeNewsLoader pokeNewsLoader;
    private IPokeNewsListener listener;
    private String url;
    private Activity activity;

    @Override
    public void getPokeNews(final Activity activity, String url, IPokeNewsListener listener) {
        this.url = url;
        this.listener = listener;
        this.activity = activity;
        activity.getLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public void getPokeVideos(Activity activity, String url, IPokeNewsListener listener) {
        this.url = url;
        this.listener = listener;
        this.activity = activity;
        activity.getLoaderManager().initLoader(2, null, this).forceLoad();
    }


    @Override
    public android.content.Loader<PokeNewsResponse> onCreateLoader(int id, Bundle args) {
        this.pokeNewsLoader = new PokeNewsLoader(activity, url);
        return this.pokeNewsLoader;
    }

    //TODO simplify this?
    @Override
    public void onLoadFinished(android.content.Loader<PokeNewsResponse> loader, PokeNewsResponse result) {
        this.pokeNewsLoader = null;
        PokeStatus status = PokeStatus.EXCEPTION;
        if (listener != null) {
            if (result != null) {
                if (result.hasException()) {
                    status = PokeStatus.EXCEPTION;
                    listener.onDataLoaded(status, new PokeNewsResponse().getItems());
                    return;
                }
                if (result.getItems().size() > 0) {
                    status = PokeStatus.FINISHED;
                } else {
                    status = PokeStatus.EMPTY;
                }
                listener.onDataLoaded(status, result.getItems());
                return;
            }
            listener.onDataLoaded(status, new PokeNewsResponse().getItems());
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<PokeNewsResponse> loader) {
        //NO-OP
    }

}
