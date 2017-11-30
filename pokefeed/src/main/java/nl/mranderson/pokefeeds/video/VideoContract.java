package nl.mranderson.pokefeeds.video;


import java.util.List;

import nl.mranderson.pokefeeds.interfaces.DataLoadedListener;
import nl.mranderson.pokefeeds.network.GenericItem;

public interface VideoContract {

    interface View {

        void setLoadingState();

        void onReadMoreClicked(String link);

        void setExceptionState();

        void setEmptyState();

        void setListState(List<GenericItem> items);
    }


    interface Presenter {

        void attach(VideoContract.View view);

        void detach();

        void onEmptyButtonTapped();

        void onRetryButtonTapped();

        void onRefreshSwiped();

        void onItemLinkTapped(String link);

        void onLoadData();
    }

    interface Interactor {

        void getVideos(String url, DataLoadedListener listener);
    }

}
