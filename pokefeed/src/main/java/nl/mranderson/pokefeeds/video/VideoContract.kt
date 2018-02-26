package nl.mranderson.pokefeeds.video


import io.reactivex.Single

interface VideoContract {
    interface View
    interface Presenter

    interface Interactor {
        fun getVideos(url: String): Single<*>
    }
}
