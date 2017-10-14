package nl.mranderson.pokefeeds.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nl.mranderson.pokefeeds.R;
import nl.mranderson.pokefeeds.interfaces.ListItemListener;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    private final ListItemListener listener;
    private List<NewsItem> items = new ArrayList<>();

    NewsAdapter(ListItemListener listener) {
        this.listener = listener;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_newsitem, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        final NewsItem item = items.get(position);
        holder.vTitle.setText(item.getTitle());
        holder.vDescription.setText(item.getDescription());
        holder.vDate.setText(item.getDate());
        holder.vLink.setOnClickListener(v -> listener.onItemTapped(item.getLink()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void update(List<NewsItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView vTitle;
        TextView vDescription;
        TextView vDate;
        TextView vLink;

        NewsItemViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.title_text);
            vDescription = (TextView) v.findViewById(R.id.description_text);
            vDate = (TextView) v.findViewById(R.id.date_text);
            vLink = (TextView) v.findViewById(R.id.link_text);
        }
    }
}
