package nl.mranderson.pokefeeds.video

import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import nl.mranderson.pokefeeds.video.model.Video
import nl.mranderson.pokefeeds.video.model.VideoState
import nl.mranderson.pokefeeds.video.model.VideoViewState

class VideoPresenter(private val videoViewState: VideoViewState, private val model: VideoService, private val presenterScheduler: Scheduler) {

    private val url = "https://www.googleapis.com/youtube/v3/search?part=snippet&fields=items(id(videoId),snippet(title,publishedAt,thumbnails(high(url))))&q=pokemon&type=video&order=date&relevanceLanguage=en-us&max-results=10&key=AIzaSyDlS5wq_ZDcOsHENER-5tsYPej6T3ziNT4&maxResults=25"
    private var disposable = CompositeDisposable()

    fun start() {
        disposable.add(model.getVideos(url)
                .observeOn(presenterScheduler)
                .subscribe(this::handleResponse, this::handleException))
    }

    fun clear() {
        disposable.clear()
    }

    private fun handleResponse(videoResponse: List<Video>) {
        videoViewState.switchState(VideoState.Done(videoResponse))
    }

    private fun handleException(throwable: Throwable) {
        videoViewState.switchState(VideoState.Error(throwable))
    }

    //TODO
    fun onItemLinkTapped(link: String) {
//        videoViewState.flowStep.postValue(FlowStep.External(link))
    }

    fun onEmptyButtonTapped() {
        start()
    }

    fun onRetryButtonTapped() {
        start()
    }

    fun onRefreshPulled() {
        start()
    }
}
