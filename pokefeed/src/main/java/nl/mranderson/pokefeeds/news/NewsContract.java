package nl.mranderson.pokefeeds.news;


import java.util.List;

interface NewsContract {

    interface View {

        void showLoadingState();

        void showExceptionState();

        void showEmptyState();

        void showListState(List<NewsItem> items);
    }

    interface Presenter {

        void attach(NewsContract.View view);

        void detach();

        void onEmptyButtonTapped();

        void onRetryButtonTapped();

        void onRefreshPulled();

        void onItemLinkTapped(String link);

    }

}
