package nl.mranderson.pokefeeds.news

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.mranderson.pokefeeds.news.model.NewsViewState
import nl.mranderson.pokefeeds.video.model.FlowStep

class NewsPresenter(private val viewState: NewsViewState, private val model: NewsInteractor) : NewsContract.Presenter {

    private val url = "https://bulbanews.bulbagarden.net/feed/news.rss"
    private lateinit var disposable: Disposable

    fun start() {
        disposable = model.getNews(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleException)
    }

    override fun clear() {
        disposable.dispose()
    }

    private fun handleResponse(response: NewsResponse) {
        viewState.isLoading.postValue(false)
        val items = response.items
        if (response.exception != null) {
            viewState.isFailed.postValue(true)
        }
        if (items != null && items.isNotEmpty()) {
            viewState.data.postValue(items)
        } else {
            viewState.isEmpty.postValue(true)
        }
    }

    private fun handleException(throwable: Throwable) {
        viewState.isLoading.postValue(false)
        viewState.isFailed.postValue(true)
    }

    override fun onItemLinkTapped(link: String) {
        viewState.flowStep.postValue(FlowStep.External(link))
    }

    override fun onEmptyButtonTapped() {
        viewState.isLoading.postValue(true)
        viewState.isEmpty.postValue(false)
        start()
    }

    override fun onRetryButtonTapped() {
        viewState.isLoading.postValue(true)
        viewState.isFailed.postValue(false)
        start()
    }

    override fun onRefreshPulled() {
        start()
    }
}
