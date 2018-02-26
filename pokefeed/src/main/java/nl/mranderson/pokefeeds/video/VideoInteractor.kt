package nl.mranderson.pokefeeds.video


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rssreader.RssReaderImpl
import io.reactivex.Single
import nl.mranderson.pokefeeds.video.model.Video
import java.util.*

class VideoInteractor : VideoContract.Interactor {

    override fun getVideos(url: String): Single<VideoResponse> {
        return Single.create { emitter ->
            val response = VideoResponse()
            try {
                val items = RssReaderImpl().getFromYoutube(url)
                val data = Gson().fromJson<Collection<Video>>(items.toString(), object : TypeToken<Collection<Video>>() {}.type)
                response.items = ArrayList(data)
            } catch (ex: Exception) {
                response.exception = ex
            }
            emitter.onSuccess(response)
        }
    }
}
