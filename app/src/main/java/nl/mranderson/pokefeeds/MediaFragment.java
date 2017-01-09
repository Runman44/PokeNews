package nl.mranderson.pokefeeds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.rssreader.RssItem;

import java.util.List;

import nl.mranderson.pokefeeds.interfaces.IPokeLinkListener;
import nl.mranderson.pokefeeds.interfaces.IPokeNewsListener;
import nl.mranderson.pokefeeds.network.PokeStatus;

public abstract class MediaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, IPokeNewsListener, IPokeLinkListener, View.OnClickListener {

    private SwipeRefreshLayout swipeLayout;
    protected IPokeNewsListener newsListener = this;
    protected IPokeLinkListener linkListener = this;
    private ProgressBar spinnerLayout;
    private RelativeLayout emptyLayout;
    private RelativeLayout exceptionLayout;
    private FirebaseAnalytics mFirebaseAnalytics;
    protected RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO butterknife
        //TODO Firebase analytics
        //TODO only update the new items? if I don't and do the whole list it will clean up the list.
        //TODO using a own object. Not using the RSSItem. Only in network package.
        //TODO make sure the controller uses the correct fields.
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.list);
        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        spinnerLayout = (ProgressBar) getView().findViewById(R.id.spinner);
        emptyLayout = (RelativeLayout) getView().findViewById(R.id.empty_container);
        exceptionLayout = (RelativeLayout) getView().findViewById(R.id.exception_container);
        Button retryButton = (Button) getView().findViewById(R.id.exception_button);
        Button emptyButton = (Button) getView().findViewById(R.id.empty_button);
        emptyButton.setOnClickListener(this);
        retryButton.setOnClickListener(this);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        getData();
        setLoadingState();
    }

    @Override
    public void onDataLoaded(PokeStatus status, List<RssItem> items) {
        swipeLayout.setRefreshing(false);

        switch (status) {
            case LOADING:
                setLoadingState();
                break;
            case EXCEPTION:
                setExceptionState();
                break;
            case EMPTY:
                setEmptyState();
                break;
            case FINISHED:
                setListState(items);
                break;
        }
    }

    @Override
    public void onRefresh() {
        onRestart();
    }

    private void onRetry() {
        onRestart();
        setLoadingState();
    }

    private void setListState(List<RssItem> items) {
        swipeLayout.setVisibility(View.VISIBLE);
        spinnerLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);

        updateAdapter(items);
    }

    private void setExceptionState() {
        exceptionLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        spinnerLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
    }

    private void setLoadingState() {
        spinnerLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);
    }

    private void setEmptyState() {
        emptyLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        spinnerLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);
    }

    @Override
    public void onReadMoreClicked(String link) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(link));
            startActivity(intent);
        } catch (Exception ex) {
            setExceptionState();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.exception_button):
                onRetry();
                break;
            case (R.id.empty_button):
                onRetry();
                break;
            default:
                //NO-OP
                break;
        }
    }

    protected abstract void getData();

    protected abstract void setAdapter(RecyclerView.Adapter adapter);

    protected abstract void updateAdapter(List<RssItem> items);

    protected abstract void onRestart();
}
