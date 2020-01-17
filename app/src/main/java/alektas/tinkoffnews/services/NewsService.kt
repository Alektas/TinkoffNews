package alektas.tinkoffnews.services

import alektas.tinkoffnews.data.entities.NewsResponse
import alektas.tinkoffnews.data.entities.PostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    fun fetchNews() : Single<NewsResponse>

    @GET("news_content")
    fun fetchPost(@Query("id") id: Long) : Single<PostResponse>
}