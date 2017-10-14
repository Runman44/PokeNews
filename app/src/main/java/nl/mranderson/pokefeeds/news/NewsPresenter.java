package nl.mranderson.pokefeeds.news;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsInteractor model;
    private NewsNavigation navigation;
    private NewsContract.View view;

    NewsPresenter(NewsInteractor model, NewsNavigation newsNavigation) {
        this.model = model;
        this.navigation = newsNavigation;
    }

    @Override
    public void attach(NewsContract.View view) {
        this.view = view;
        this.view.showLoadingState();
        getNews();
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void onEmptyButtonTapped() {
        this.view.showLoadingState();
        getNews();
    }

    @Override
    public void onRetryButtonTapped() {
        this.view.showLoadingState();
        getNews();
    }

    @Override
    public void onRefreshPulled() {
        getNews();
    }

    @Override
    public void onItemLinkTapped(String link) {
        this.navigation.openDetailedPage(link);
    }

    private void getNews() {
        final Observable<List<NewsItem>> serverDownloadObservable = Observable.create(emitter -> {

            try {
                List<NewsItem> newsItems = this.model.getNews();
                emitter.onNext(newsItems);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        serverDownloadObservable.
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(this::onLoadFinished);
    }

    private void onLoadFinished(List<NewsItem> result) {
        if (result == null) {
            this.view.showExceptionState();
            return;
        }
        if (result.size() > 0) {
            this.view.showListState(result);
        } else {
            this.view.showEmptyState();
        }
    }
}
