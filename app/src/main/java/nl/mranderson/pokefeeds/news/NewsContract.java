package nl.mranderson.pokefeeds.news;


import java.util.List;

import nl.mranderson.pokefeeds.network.GenericItem;

public interface NewsContract {

    interface View {

        void setLoadingState();

        void onReadMoreClicked(String link);

        void setExceptionState();

        void setEmptyState();

        void setListState(List<GenericItem> items);
    }

    interface Presenter {

        void attach(NewsContract.View view);

        void detach();

        void onEmptyButtonTapped();

        void onRetryButtonTapped();

        void onRefreshSwiped();

        void onItemLinkTapped(String link);

        void onLoadData();
    }

}
