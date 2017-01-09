package nl.mranderson.pokefeeds;

import android.support.v7.widget.RecyclerView;

import com.rssreader.RssItem;

import java.util.List;

import nl.mranderson.pokefeeds.network.PokeNewsController;

public class NewsFragment extends MediaFragment {

    private NewsAdapter mAdapter;
    private PokeNewsController pokeNewsController;
    private String CONTENT_URL;

    @Override
    protected void getData() {
        CONTENT_URL = "http://bulbanews.bulbagarden.net/feed/news.rss";

        mAdapter = new NewsAdapter(getActivity(), super.linkListener);
        setAdapter(mAdapter);

        pokeNewsController = new PokeNewsController();
        pokeNewsController.getPokeNews(getActivity(), CONTENT_URL, super.newsListener);
    }

    @Override
    protected void setAdapter(RecyclerView.Adapter adapter) {
        super.mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void updateAdapter(List<RssItem> items) {
        mAdapter.update(items);
    }

    @Override
    protected void onRestart() {
        pokeNewsController.getPokeNews(getActivity(), CONTENT_URL, newsListener);
    }
}
