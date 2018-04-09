package nl.mranderson.pokefeeds.video

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import nl.mranderson.pokefeeds.R
import nl.mranderson.pokefeeds.interfaces.ListItemListener
import nl.mranderson.pokefeeds.video.model.Video

class VideoAdapter(private val context: Context?, private val listener: ListItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_videoitem, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindNews(holder, position)
    }

    private fun bindNews(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (item is Video && holder is NewsItemViewHolder) {
            holder.vTitle.text = item.title
            Picasso.with(context)
                    .load(item.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(holder.vImage)
            holder.vImage.setOnClickListener { v -> listener.onItemTapped(item.link) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: ArrayList<Any>?) {
        if (newItems != null) {
            val diffResult = DiffUtil.calculateDiff(VideoDiffCallback(items, newItems))
            diffResult.dispatchUpdatesTo(this)
            items = newItems
        }
    }

    fun addItem(video: Video) {
        val oldItems = deepCopy(items)
        items.add(video)

        val diffResult = DiffUtil.calculateDiff(VideoDiffCallback(oldItems, items))
        diffResult.dispatchUpdatesTo(this)
    }

    private fun deepCopy(items: ArrayList<Any>): ArrayList<Any> {
        val list = ArrayList<Any>()
        for (item in items) {
            if (item is Video) {
                list.add(Video(item.title, item.description, item.link, item.pubDate, item.imageUrl))
            }
        }
        return list
    }

    inner class NewsItemViewHolder internal constructor(v: View) : RecyclerView.ViewHolder(v) {
        internal var vTitle: TextView
        internal var vImage: ImageView

        init {
            vTitle = v.findViewById(R.id.title_text)
            vImage = v.findViewById(R.id.item_image)
        }
    }
}
