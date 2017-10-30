package nl.mranderson.pokefeeds.video;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nl.mranderson.pokefeeds.R;
import nl.mranderson.pokefeeds.interfaces.ListItemListener;
import nl.mranderson.pokefeeds.network.GenericItem;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.NewsItemViewHolder> {

    private final Context context;
    private final ListItemListener listener;
    private List<GenericItem> items = new ArrayList<>();

    public VideoAdapter(Context context, ListItemListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_videoitem, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        final GenericItem item = items.get(position);
        holder.vTitle.setText(item.getTitle());
        Picasso.with(context)
                .load(item.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.vImage);
        holder.vImage.setOnClickListener(v -> listener.onItemTapped(item.getLink()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(List<GenericItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;
        ImageView vImage;

        NewsItemViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.title_text);
            vImage = (ImageView) v.findViewById(R.id.item_image);
        }
    }
}
