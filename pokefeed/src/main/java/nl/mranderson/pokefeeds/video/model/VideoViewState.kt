package nl.mranderson.pokefeeds.video.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Scheduler
import nl.mranderson.pokefeeds.video.VideoService
import nl.mranderson.pokefeeds.video.VideoPresenter

class VideoViewState {

    //TODO learn today.
    var isLoading = MutableLiveData<Boolean>()
    var isFailed = MutableLiveData<Boolean>()
    var data = MutableLiveData<List<Video>>()
    var flowStep = MutableLiveData<FlowStep>()
    val isEmpty = MutableLiveData<Boolean>()

}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(private val videoService: VideoService, private val videoViewState: VideoViewState, private val presenterScheduler : Scheduler) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(VideoPresenter(videoViewState, videoService, presenterScheduler), videoViewState) as T
    }
}

class VideoViewModel(val presenter: VideoPresenter, val videoViewState: VideoViewState) : ViewModel() {

    init {
        presenter.start()
    }

    override fun onCleared() {
        presenter.clear()
        super.onCleared()
    }
}