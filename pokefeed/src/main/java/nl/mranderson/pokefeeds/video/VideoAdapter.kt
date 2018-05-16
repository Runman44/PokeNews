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
    private var items = List<Video>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_videoitem, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindNews(holder, position)
    }

    private fun bindNews(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is VideoItemViewHolder) {
            holder.title.text = item.title
            Picasso.with(context)
                    .load(item.imageUrl)
                    .fit()
                    .centerCrop()
                    .into(holder.image)
            holder.image.setOnClickListener { _ -> listener.onItemTapped(item.link) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<Video>?) {
        if (newItems != null) {
            val diffResult = DiffUtil.calculateDiff(VideoDiffCallback(items, newItems))
            diffResult.dispatchUpdatesTo(this)
            items = newItems
        }
    }

    inner class VideoItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView = v.findViewById(R.id.title_text)
        var image: ImageView = v.findViewById(R.id.item_image)
    }
}
