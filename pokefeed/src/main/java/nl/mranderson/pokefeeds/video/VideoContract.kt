package nl.mranderson.pokefeeds.video


import io.reactivex.Single

interface VideoContract {

    interface Presenter {

        fun onEmptyButtonTapped()

        fun onRetryButtonTapped()

        fun onRefreshPulled()

        fun onItemLinkTapped(link: String)

        fun clear()
    }

    interface Interactor {
        fun getVideos(url: String): Single<*>
    }
}