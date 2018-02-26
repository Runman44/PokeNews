package nl.mranderson.pokefeeds.video.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import nl.mranderson.pokefeeds.video.VideoPresenter


class VideoViewState {

    var isLoading = MutableLiveData<Boolean>()
    var isFailed = MutableLiveData<Boolean>()
    var data = MutableLiveData<List<Video>>()
    var flowStep = MutableLiveData<FlowStep>()
    val isEmpty = MutableLiveData<Boolean>()

}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(private val videoPresenter: VideoPresenter, private val videoViewState: VideoViewState) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
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