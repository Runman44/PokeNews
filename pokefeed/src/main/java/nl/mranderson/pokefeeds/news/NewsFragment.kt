package nl.mranderson.pokefeeds.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list.*
import nl.mranderson.pokefeeds.R
import nl.mranderson.pokefeeds.news.model.NewsItem
import nl.mranderson.pokefeeds.news.model.NewsViewModel
import nl.mranderson.pokefeeds.news.model.NewsViewModelFactory
import nl.mranderson.pokefeeds.news.model.NewsViewState

//TODO only update the new items? if I don't and do the whole list it will clean up the list. DIFFUTILS
//TODO composite adapter for ads? perhaps.
class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var mAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewState = NewsViewState()
        val presenter = NewsPresenter(viewState, NewsInteractor())
        viewModel = ViewModelProviders.of(this, NewsViewModelFactory(presenter, viewState)).get(NewsViewModel::class.java)
//        videoNavigation = NewsNavigation(activity)

        swipe_container.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

        setList()
        setListeners()
        bindViews()
    }

    private fun setList() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = layoutManager
        list.setHasFixedSize(true)

        mAdapter = NewsAdapter { link -> viewModel.presenter.onItemLinkTapped(link) }
        list.adapter = mAdapter
    }

    private fun bindViews() {
        viewModel.viewState.isLoading.observe(this, Observer { isLoading -> showLoading(isLoading) })
        viewModel.viewState.isFailed.observe(this, Observer { isFailed -> showError(isFailed) })
//        viewModel.viewState.flowStep.observe(this, Observer { step -> videoNavigation.doStep(step) })
        viewModel.viewState.data.observe(this, Observer { response -> if (response != null) showData(response) else showEmpty() })
    }

    private fun showData(response: List<NewsItem>?) {
        swipe_container.visibility = View.VISIBLE
        swipe_container.isRefreshing = false
        mAdapter.update(response)
    }

    private fun showLoading(loading: Boolean?) {
        if (loading != null && loading) {
            spinner.visibility = View.VISIBLE
        } else {
            spinner.visibility = View.GONE
        }
    }

    private fun showEmpty() {
        empty_container.visibility = View.VISIBLE
    }

    private fun showError(failed: Boolean?) {
        if (failed != null && failed) {
            exception_container.visibility = View.VISIBLE
        } else {
            exception_container.visibility = View.GONE
        }
    }

    private fun setListeners() {
        empty_button.setOnClickListener { viewModel.presenter.onEmptyButtonTapped() }
        exception_button.setOnClickListener { viewModel.presenter.onRetryButtonTapped() }
        swipe_container.setOnRefreshListener { viewModel.presenter.onRefreshPulled() }
    }
}
