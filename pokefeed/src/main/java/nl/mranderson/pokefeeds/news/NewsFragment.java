package nl.mranderson.pokefeeds.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.List;

import nl.mranderson.pokefeeds.R;
import nl.mranderson.pokefeeds.news.data.NewsDataRepository;
import nl.mranderson.pokefeeds.news.data.NewsRepository;

//TODO only update the new items? if I don't and do the whole list it will clean up the list.
//TODO composite adapter for ads?
public class NewsFragment extends Fragment implements NewsContract.View {

    private SwipeRefreshLayout swipeLayout;
    private ProgressBar spinnerLayout;
    private RelativeLayout emptyLayout;
    private RelativeLayout exceptionLayout;
    private RecyclerView mRecyclerView;
    private NewsPresenter presenter;
    private NewsAdapter mAdapter;
    private Button retryButton;
    private Button emptyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    @SuppressWarnings({"ConstantConditions"})
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = getView().findViewById(R.id.list);
        swipeLayout = getView().findViewById(R.id.swipe_container);
        spinnerLayout = getView().findViewById(R.id.spinner);
        emptyLayout = getView().findViewById(R.id.empty_container);
        exceptionLayout = getView().findViewById(R.id.exception_container);
        retryButton = getView().findViewById(R.id.exception_button);
        emptyButton = getView().findViewById(R.id.empty_button);

        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        setList();
        setListeners();

        presenter = createPresenter();
        presenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }

    private void setList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new NewsAdapter(link -> presenter.onItemLinkTapped(link));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setListeners() {
        emptyButton.setOnClickListener(v -> presenter.onEmptyButtonTapped());

        retryButton.setOnClickListener(v -> presenter.onRetryButtonTapped());

        swipeLayout.setOnRefreshListener(() -> presenter.onRefreshPulled());
    }

    private NewsPresenter createPresenter() {
        NewsNavigation newsNavigation = new NewsNavigationImpl(getActivity());
        NewsRepository<NewsItem> repository =  NewsDataRepository.getInstance();
        NewsInteractor newsInteractor = new NewsInteractor(repository);
        return new NewsPresenter(newsInteractor, newsNavigation);
    }

    public void showListState(List<NewsItem> items) {
        swipeLayout.setRefreshing(false);
        swipeLayout.setVisibility(View.VISIBLE);
        spinnerLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);

        mAdapter.update(items);
    }

    public void showExceptionState() {
        exceptionLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        spinnerLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
    }

    public void showLoadingState() {
        spinnerLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);
    }

    public void showEmptyState() {
        emptyLayout.setVisibility(View.VISIBLE);
        swipeLayout.setVisibility(View.GONE);
        spinnerLayout.setVisibility(View.GONE);
        exceptionLayout.setVisibility(View.GONE);
    }
}