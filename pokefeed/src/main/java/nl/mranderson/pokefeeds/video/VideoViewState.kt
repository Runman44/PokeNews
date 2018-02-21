package nl.mranderson.pokefeeds.video

import android.arch.lifecycle.ViewModel


class VideoViewState {




}

class VideoViewModelFactory : View {

}


class VideoViewModel(private val presenter: VideoPresenter, private val videoViewState: VideoViewState) : ViewModel() {

    init {
        presenter.start()
    }


    override fun onCleared() {
        presenter.clear()
        super.onCleared()
    }

}