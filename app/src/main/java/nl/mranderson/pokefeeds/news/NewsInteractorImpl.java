package nl.mranderson.pokefeeds.news;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rssreader.RssReader;
import com.rssreader.RssReaderImpl;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;
import nl.mranderson.pokefeeds.network.GenericItem;
import nl.mranderson.pokefeeds.network.GenericResponse;
import nl.mranderson.pokefeeds.network.GenericStatus;

public class NewsInteractorImpl implements NewsInteractor {

    private DataLoadedListener listener;

    @Override
    public void getNews(final String url, DataLoadedListener listener) {
        this.listener = listener;

        ExecutorService pool = Executors.newFixedThreadPool(1);

        Callable<GenericResponse> callable = new Callable<GenericResponse>() {
            @Override
            public GenericResponse call() throws Exception {
                GenericResponse response = new GenericResponse();
                try {
                    RssReader rssReader = new RssReaderImpl();
                    final JSONArray items = rssReader.getFromRssFeed(url);
                    Gson gson = new Gson();
                    Collection<GenericItem> data = gson.fromJson(items.toString(), new TypeToken<Collection<GenericItem>>() {
                    }.getType());
                    List<GenericItem> myNodeList = new ArrayList<>(data);
                    response.setItems(myNodeList);
                } catch (Exception ex) {
                    response.setException(ex);
                }
                return response;
            }
        };

        Future<GenericResponse> future = pool.submit(callable);
        GenericResponse response = new GenericResponse();
        try {
            response = future.get();
        } catch (Exception ex) {
            response.setException(ex);
        }

        onLoadFinished(response);
    }

    private void onLoadFinished(GenericResponse result) {
        if (listener != null) {
            if (result == null || result.hasException()) {
                listener.onDataLoaded(GenericStatus.EXCEPTION, new GenericResponse().getItems());
                return;
            }
            if (result.getItems().size() > 0) {
                listener.onDataLoaded(GenericStatus.FINISHED, result.getItems());
            } else {
                listener.onDataLoaded(GenericStatus.EMPTY, result.getItems());
            }
        }
    }
}
