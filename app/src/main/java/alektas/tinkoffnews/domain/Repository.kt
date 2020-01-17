package alektas.tinkoffnews.domain

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.data.entities.NewsPost
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun observeNews(): Observable<List<NewsInfo>>
    fun fetchNews()
    fun fetchNewsPost(id: Long): Single<NewsPost>
}