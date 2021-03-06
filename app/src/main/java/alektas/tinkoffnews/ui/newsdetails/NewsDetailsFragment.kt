package alektas.tinkoffnews.ui.newsdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import alektas.tinkoffnews.R
import alektas.tinkoffnews.data.entities.NewsPost
import alektas.tinkoffnews.ui.NewsFragment
import alektas.tinkoffnews.ui.ProcessState
import android.content.Intent
import android.view.MenuItem
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.news_details_fragment.*

private const val ARG_NEWS_ID = "param1"

class NewsDetailsFragment : Fragment() {
    private var newsId: Long? = null
    private lateinit var viewModel: NewsDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsId = it.getLong(ARG_NEWS_ID)
        }
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.navigateUpTo(Intent(requireContext(), NewsFragment::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(NewsDetailsViewModel::class.java)
        newsId?.let { viewModel.fetchPost(it) }

        viewModel.newsPost.observe(viewLifecycleOwner, Observer { bind(it) })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when (it.state) {
                ProcessState.STARTED -> progress_bar.visibility = View.VISIBLE
                ProcessState.SUCCESS -> progress_bar.visibility = View.INVISIBLE
                ProcessState.ERROR -> {
                    progress_bar.visibility = View.INVISIBLE
                    post_error.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun bind(info: NewsPost) {
        val headline = info.info?.headline
        val content = info.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
        post_headline.text = headline ?: getString(R.string.news_headline_placeholder)
        post_content.text = content ?: getString(R.string.news_post_body_placeholder)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param gameId id of the selected news post.
         * @return A new instance of fragment NewsFragment.
         */
        @JvmStatic
        fun newInstance(gameId: Long) =
            NewsDetailsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_NEWS_ID, gameId)
                }
            }
    }

}
