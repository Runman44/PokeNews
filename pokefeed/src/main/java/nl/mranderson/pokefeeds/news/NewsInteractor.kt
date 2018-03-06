package nl.mranderson.pokefeeds.news

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rssreader.RssReaderImpl
import io.reactivex.Single
import nl.mranderson.pokefeeds.news.model.NewsItem
import java.util.*

class NewsInteractor : NewsContract.Interactor {

    override fun getNews(url: String): Single<NewsResponse> {
        return Single.create { emitter ->
            val response = NewsResponse()
            try {
                val items = RssReaderImpl().getFromRssFeed(url)
                val data = Gson().fromJson<Collection<NewsItem>>(items.toString(), object : TypeToken<Collection<NewsItem>>() {}.type)
                response.items = ArrayList(data)
            } catch (ex: Exception) {
                response.exception = ex
            }
            emitter.onSuccess(response)
        }
    }
}
