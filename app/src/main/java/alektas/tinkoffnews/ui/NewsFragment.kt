package alektas.tinkoffnews.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import alektas.tinkoffnews.R
import alektas.tinkoffnews.ui.newsdetails.NewsDetailsFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_fragment.*

class NewsFragment : Fragment(), ItemListener {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsAdapter = NewsAdapter(this)

        news_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        swipe_refresh.setOnRefreshListener {
            viewModel.fetchNews()
        }

        viewModel.newsLive.observe(viewLifecycleOwner, Observer {
            newsAdapter.news = it
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                ProcessState.STARTED -> {
                    post_error.visibility = View.INVISIBLE
                    swipe_refresh.isRefreshing = true
                }
                ProcessState.SUCCESS -> swipe_refresh.isRefreshing = false
                ProcessState.ERROR -> {
                    swipe_refresh.isRefreshing = false
                    if (newsAdapter.news.isEmpty()) {
                        post_error.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun onItemSelected(id: Long) {
        showItemDetails(id)
    }

    private fun showItemDetails(id: Long) {
        val f = NewsDetailsFragment.newInstance(id)
        fragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.content_container, f, "newsDetails")
            ?.commit()
    }

}
