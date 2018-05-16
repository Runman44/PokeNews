package nl.mranderson.pokefeeds.video.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.reactivex.Scheduler
import nl.mranderson.pokefeeds.video.VideoService
import nl.mranderson.pokefeeds.video.VideoPresenter

class VideoViewState {

    var currentState = MutableLiveData<VideoState>()

    fun switchState(newState: VideoState) {
        currentState.value = newState
    }
}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(private val videoService: VideoService, private val presenterScheduler : Scheduler) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val videoViewState = VideoViewState()
        val videoPresenter = VideoPresenter(videoViewState, videoService, presenterScheduler)
        return VideoViewModel(videoPresenter, videoViewState) as T
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