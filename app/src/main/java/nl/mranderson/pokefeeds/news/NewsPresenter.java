package nl.mranderson.pokefeeds.news;

import java.util.List;

import nl.mranderson.pokefeeds.news.data.NewsRepository;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsRepository<NewsItem> model;
    private NewsNavigation navigation;
    private NewsContract.View view;

    NewsPresenter(NewsRepository<NewsItem> model, NewsNavigation newsNavigation) {
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

    //TODO this should be in repo right? this is business logic bcs if you rotate this call will be done again. Even more so, its specific for a network call right?
    private void getNews() {
        List<NewsItem> all = this.model.getAll();
        onLoadFinished(all);
        //TODO can use Single then right?
//        final Observable<List<NewsItem>> serverDownloadObservable = Observable.create(emitter -> {
//
//            try {
        //TODO why is this here? should some thing not be a observable?
//                List<NewsItem> newsItems = this.model.getAll();
//                emitter.onNext(newsItems);
//                emitter.onComplete();
//            } catch (Exception e) {
//                emitter.onError(e);
//            }
//        });
//        serverDownloadObservable.
//                observeOn(AndroidSchedulers.mainThread()).
//                subscribeOn(Schedulers.io()).
//                subscribe(this::onLoadFinished);
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
