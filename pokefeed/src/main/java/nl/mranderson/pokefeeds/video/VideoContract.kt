package nl.mranderson.pokefeeds.video


interface VideoContract {

    interface Presenter {

        fun onEmptyButtonTapped()

        fun onRetryButtonTapped()

        fun onRefreshPulled()

        fun onItemLinkTapped(link: String)

        fun clear()
    }
}
