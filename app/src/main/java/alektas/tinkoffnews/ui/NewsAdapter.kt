package alektas.tinkoffnews.ui

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val itemListener: ItemListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var news: List<NewsInfo> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItem(news[position])
        holder.itemView.setOnClickListener { news[position].id?.let { itemListener.onItemSelected(it) } }
    }

    override fun getItemCount(): Int = news.size

    override fun getItemId(position: Int): Long {
        return news[position].id ?: 0
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val preview: TextView = view.news_preview
        private val date: TextView = view.news_date

        fun bindItem(item: NewsInfo?) {
            preview.text =
                item?.body ?: itemView.context.getString(R.string.news_post_body_placeholder)
            date.text = item?.publicationDate?.toString()
                ?: itemView.context.getString(R.string.news_post_publication_date_placeholder)
        }
    }
}