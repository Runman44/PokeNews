package nl.mranderson.pokefeeds.news;

import java.util.List;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;
import nl.mranderson.pokefeeds.network.GenericItem;
import nl.mranderson.pokefeeds.network.GenericStatus;

//TODO try WeakReferences
public class NewsPresenter implements NewsContract.Presenter {

    private static final String CONTENT_URL = "http://bulbanews.bulbagarden.net/feed/news.rss";

    private final NewsInteractor model;
    private NewsContract.View view;
    private DataLoadedListener pokeNewsListener;

    NewsPresenter(NewsInteractor model) {
        this.model = model;
        addPokeNewsListener();
    }

    @Override
    public void attach(NewsContract.View view) {
        this.view = view;
        this.view.setLoadingState();
        this.model.getNews(CONTENT_URL, pokeNewsListener);
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void onEmptyButtonTapped() {
        this.model.getNews(CONTENT_URL, pokeNewsListener);
        this.view.setLoadingState();
    }

    @Override
    public void onRetryButtonTapped() {
        this.model.getNews(CONTENT_URL, pokeNewsListener);
        this.view.setLoadingState();
    }

    @Override
    public void onLoadData() {
        this.view.setLoadingState();
        this.model.getNews(CONTENT_URL, pokeNewsListener);
    }

    @Override
    public void onRefreshSwiped() {
        this.model.getNews(CONTENT_URL, pokeNewsListener);
    }

    @Override
    public void onItemLinkTapped(String link) {
        this.view.onReadMoreClicked(link);
    }

    private void addPokeNewsListener() {
        pokeNewsListener = new DataLoadedListener() {
            @Override
            public void onDataLoaded(GenericStatus status, List<GenericItem> items) {
                handleData(status, items);
            }
        };
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
