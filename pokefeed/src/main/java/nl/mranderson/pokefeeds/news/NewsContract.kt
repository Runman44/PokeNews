package nl.mranderson.pokefeeds.news

import io.reactivex.Single

internal interface NewsContract {

    interface Presenter {

        fun onEmptyButtonTapped()

        fun onRetryButtonTapped()

        fun onRefreshPulled()

        fun onItemLinkTapped(link: String)

        fun clear()
    }

    interface Interactor {
        fun getNews(url: String): Single<NewsResponse>
    }

}
