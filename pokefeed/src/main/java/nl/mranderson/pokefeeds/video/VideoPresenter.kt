package nl.mranderson.pokefeeds.video

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.mranderson.pokefeeds.video.model.FlowStep
import nl.mranderson.pokefeeds.video.model.VideoViewState

class VideoPresenter(private val videoViewState: VideoViewState, private val model: VideoInteractor) : VideoContract.Presenter {

    private val url = "https://www.googleapis.com/youtube/v3/search?part=snippet&fields=items(id(videoId),snippet(title,publishedAt,thumbnails(high(url))))&q=pokemon&type=video&order=date&relevanceLanguage=en-us&max-results=10&key=AIzaSyDlS5wq_ZDcOsHENER-5tsYPej6T3ziNT4&maxResults=25"
    private lateinit var disposable: Disposable

    fun start() {
        disposable = model.getVideos(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleException)
    }

    fun clear() {
        disposable.dispose()
    }

    private fun handleResponse(videoResponse: VideoResponse) {
        videoViewState.isLoading.postValue(false)
        val items = videoResponse.items
        if (videoResponse.exception != null) {
            videoViewState.isFailed.postValue(true)
        }
        if (items != null && items.isNotEmpty()) {
            videoViewState.data.postValue(items)
        } else {
            videoViewState.isEmpty.postValue(true)
        }
    }

    private fun handleException(throwable: Throwable) {
        videoViewState.isLoading.postValue(false)
        videoViewState.isFailed.postValue(true)
    }

    fun onItemLinkTapped(link: String?) {
        videoViewState.flowStep.postValue(FlowStep.External(link))
    }

    fun onEmptyButtonTapped() {
        videoViewState.isLoading.postValue(true)
        videoViewState.isEmpty.postValue(false)
        start()
    }

    fun onRetryButtonTapped() {
        videoViewState.isLoading.postValue(true)
        videoViewState.isFailed.postValue(false)
        start()
    }

    fun onRefreshSwiped() {
        start()
    }
}
