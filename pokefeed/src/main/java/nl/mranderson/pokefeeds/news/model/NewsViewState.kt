package nl.mranderson.pokefeeds.news.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import nl.mranderson.pokefeeds.news.NewsPresenter
import nl.mranderson.pokefeeds.video.model.FlowStep


class NewsViewState {

    var isLoading = MutableLiveData<Boolean>()
    var isFailed = MutableLiveData<Boolean>()
        var data = MutableLiveData<List<NewsItem>>()
    var flowStep = MutableLiveData<FlowStep>()
    val isEmpty = MutableLiveData<Boolean>()

}

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(private val presenter: NewsPresenter, private val viewState: NewsViewState) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(presenter, viewState) as T
    }
}


class NewsViewModel(val presenter: NewsPresenter, val viewState: NewsViewState) : ViewModel() {

    init {
        presenter.start()
    }

    override fun onCleared() {
        presenter.clear()
        super.onCleared()
    }
}