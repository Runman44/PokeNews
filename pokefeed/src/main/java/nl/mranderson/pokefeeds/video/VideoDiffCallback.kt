package nl.mranderson.pokefeeds.video

import android.support.v7.util.DiffUtil
import nl.mranderson.pokefeeds.video.model.Video

class VideoDiffCallback(private val oldItems: List<Any>, private val newItems: List<Any>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (newItems[newItemPosition] is Video && oldItems[oldItemPosition] is Video)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (newItems[newItemPosition] == oldItems[oldItemPosition])
    }
}