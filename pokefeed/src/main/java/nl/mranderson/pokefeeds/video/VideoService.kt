package nl.mranderson.pokefeeds.video

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rssreader.RssReaderImpl
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import nl.mranderson.pokefeeds.video.model.Video
import java.util.*

class VideoService(private val serviceScheduler : Scheduler) {

    fun getVideos(url: String): Single<List<Video>> {
        return Single.create {
//            val items = RssReaderImpl().getFromYoutube(url)
//            Gson().fromJson<Collection<Video>>(items.toString(), object : TypeToken<Collection<Video>>() {}.type)
        }.subscribeOn(serviceScheduler)
    }
}
