package nl.mranderson.pokefeeds.news;

import java.util.List;

public class NewsPresenter implements NewsContract.Presenter {

    private final NewsInteractor useCase;
    private NewsNavigation navigation;
    private NewsContract.View view;

    NewsPresenter(NewsInteractor useCase, NewsNavigation newsNavigation) {
        this.useCase = useCase;
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
        //TODO logicin the interactor?
        List<NewsItem> newsItems = useCase.getNewsItems();
        if (newsItems != null && newsItems.size() > 0) {
            view.showListState(newsItems);
        } else {
            view.showEmptyState();
        }
    }
}
