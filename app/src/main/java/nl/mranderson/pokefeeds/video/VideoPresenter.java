package nl.mranderson.pokefeeds.video;

import java.util.List;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;
import nl.mranderson.pokefeeds.network.GenericItem;
import nl.mranderson.pokefeeds.network.GenericStatus;

//TODO try WeakReferences
public class VideoPresenter implements VideoContract.Presenter {

    private static final String CONTENT_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&fields=items(id(videoId),snippet(title,publishedAt,thumbnails(high(url))))&q=pokemon&type=video&order=date&relevanceLanguage=en-us&max-results=10&key=AIzaSyDlS5wq_ZDcOsHENER-5tsYPej6T3ziNT4&maxResults=25";

    private final VideoInteractor model;
    private VideoContract.View view;
    private DataLoadedListener pokeNewsListener;

    public VideoPresenter(VideoInteractor model) {
        this.model = model;

        addPokeNewsListener();
    }

    @Override
    public void attach(VideoContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void onEmptyButtonTapped() {
        this.model.getVideos(CONTENT_URL, pokeNewsListener);
        this.view.setLoadingState();
    }

    @Override
    public void onRetryButtonTapped() {
        this.model.getVideos(CONTENT_URL, pokeNewsListener);
        this.view.setLoadingState();
    }

    @Override
    public void onRefreshSwiped() {
        this.model.getVideos(CONTENT_URL, pokeNewsListener);
    }

    @Override
    public void onItemLinkTapped(String link) {
        this.view.onReadMoreClicked(link);
    }

    @Override
    public void onLoadData() {
        this.view.setLoadingState();
        this.model.getVideos(CONTENT_URL, pokeNewsListener);
    }

    private void addPokeNewsListener() {
        //TODO this is bad, fix with RxJava or..?
        pokeNewsListener = this::handleData;
    }

    private void handleData(GenericStatus status, List<GenericItem> items) {
        switch (status) {
            case LOADING:
                this.view.setLoadingState();
                break;
            case EMPTY:
                this.view.setEmptyState();
                break;
            case FINISHED:
                this.view.setListState(items);
                break;
            case EXCEPTION:
            default:
                this.view.setExceptionState();
                break;
        }
    }
}
