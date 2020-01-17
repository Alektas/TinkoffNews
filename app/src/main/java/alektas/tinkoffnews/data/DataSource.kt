package alektas.tinkoffnews.data

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.data.entities.NewsPost
import io.reactivex.Single

interface DataSource {
    fun fetchNews(): Single<List<NewsInfo>>
    fun fetchPost(id: Long): Single<NewsPost>
}