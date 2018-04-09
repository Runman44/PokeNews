package nl.mranderson.pokefeeds.video

import android.support.v7.util.DiffUtil
import nl.mranderson.pokefeeds.video.model.Video
import nl.mranderson.pokefeeds.video.model.VideoDetail

class VideoDiffCallback(private val oldItems: List<Any>, private val newItems: List<Any>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        var returnValue = false
        if (newItem is Video && oldItem is Video) {
            returnValue = true
        }
        if (newItem is VideoDetail && oldItem is VideoDetail) {
            returnValue =  true
        }
        return returnValue
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        var returnValue = false
        if (newItem == oldItem) {
            returnValue = true
        }
        return returnValue
    }


}