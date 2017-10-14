package nl.mranderson.pokefeeds.video;import android.content.Intent;import android.net.Uri;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v4.app.Fragment;import android.support.v4.widget.SwipeRefreshLayout;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.Button;import android.widget.ProgressBar;import android.widget.RelativeLayout;import com.google.firebase.analytics.FirebaseAnalytics;import java.util.List;import nl.mranderson.pokefeeds.R;import nl.mranderson.pokefeeds.network.GenericItem;//TODO butterknife//TODO Firebase analytics//TODO only update the new items? if I don't and do the whole list it will clean up the list.//TODO using a own object. Not using the ParsedItem. Only in network package.//TODO make sure the controller uses the correct fields.//TODO use MVP pattern.public class VideoFragment extends Fragment implements VideoContract.View {    private SwipeRefreshLayout swipeLayout;    private ProgressBar spinnerLayout;    private RelativeLayout emptyLayout;    private RelativeLayout exceptionLayout;    private FirebaseAnalytics mFirebaseAnalytics;    protected RecyclerView mRecyclerView;    private VideoPresenter presenter;    private VideoAdapter mAdapter;    private Button emptyButton;    private Button retryButton;    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        return inflater.inflate(R.layout.fragment_list, container, false);    }    @Override    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {        super.onViewCreated(view, savedInstanceState);        mRecyclerView = (RecyclerView) getView().findViewById(R.id.list);        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);        spinnerLayout = (ProgressBar) getView().findViewById(R.id.spinner);        emptyLayout = (RelativeLayout) getView().findViewById(R.id.empty_container);        exceptionLayout = (RelativeLayout) getView().findViewById(R.id.exception_container);        retryButton = (Button) getView().findViewById(R.id.exception_button);        emptyButton = (Button) getView().findViewById(R.id.empty_button);        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,                android.R.color.holo_green_light,                android.R.color.holo_orange_light,                android.R.color.holo_red_light);        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);        mRecyclerView.setLayoutManager(layoutManager);        mRecyclerView.setHasFixedSize(true);        mAdapter = new VideoAdapter(getActivity(), link -> presenter.onItemLinkTapped(link));        mRecyclerView.setAdapter(mAdapter);        setListeners();        presenter = createPresenter();        presenter.attach(this);        presenter.onLoadData();    }    private void setListeners() {        emptyButton.setOnClickListener(v -> presenter.onEmptyButtonTapped());        retryButton.setOnClickListener(v -> presenter.onRetryButtonTapped());        swipeLayout.setOnRefreshListener(() -> presenter.onRefreshSwiped());    }    @Override    public void onDestroyView() {        super.onDestroyView();        presenter.detach();    }    @Override    public void onReadMoreClicked(String link) {        try {            Intent intent = new Intent(Intent.ACTION_VIEW);            intent.setData(Uri.parse(link));            startActivity(intent);        } catch (Exception ex) {            setExceptionState();        }    }    private VideoPresenter createPresenter() {        VideoInteractor model = new VideoInteractor();        return new VideoPresenter(model);    }    public void setListState(List<GenericItem> items) {        swipeLayout.setRefreshing(false);        swipeLayout.setVisibility(View.VISIBLE);        spinnerLayout.setVisibility(View.GONE);        emptyLayout.setVisibility(View.GONE);        exceptionLayout.setVisibility(View.GONE);        mAdapter.update(items);    }    public void setExceptionState() {        exceptionLayout.setVisibility(View.VISIBLE);        swipeLayout.setVisibility(View.GONE);        spinnerLayout.setVisibility(View.GONE);        emptyLayout.setVisibility(View.GONE);    }    public void setLoadingState() {        spinnerLayout.setVisibility(View.VISIBLE);        swipeLayout.setVisibility(View.GONE);        emptyLayout.setVisibility(View.GONE);        exceptionLayout.setVisibility(View.GONE);    }    public void setEmptyState() {        emptyLayout.setVisibility(View.VISIBLE);        swipeLayout.setVisibility(View.GONE);        spinnerLayout.setVisibility(View.GONE);        exceptionLayout.setVisibility(View.GONE);    }}