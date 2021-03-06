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
import nl.mranderson.pokefeeds.news.model.NewsItem;

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
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.date.setText(item.getDate());
//        holder.link.setOnClickListener(v -> listener.onItemTapped(item.getLink()));
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
        private TextView title;
        private TextView description;
        private TextView date;
//        private TextView link;

        NewsItemViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.title_text);
            title = view.findViewById(R.id.description_text_title);
            description = view.findViewById(R.id.description_text_description);
//            date = view.findViewById(R.id.date_text);
//            link = view.findViewById(R.id.link_text);
        }
    }
}
