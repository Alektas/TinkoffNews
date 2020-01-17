package alektas.tinkoffnews.data.remote

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.App
import alektas.tinkoffnews.BuildConfig
import alektas.tinkoffnews.data.DataSource
import alektas.tinkoffnews.data.entities.NewsPost
import alektas.tinkoffnews.data.entities.NewsResponse
import alektas.tinkoffnews.data.entities.PostResponse
import alektas.tinkoffnews.services.NewsService
import io.reactivex.Single
import javax.inject.Inject

class RemoteNewsSource : DataSource {
    @Inject
    lateinit var newsService: NewsService

    init {
        App.appComponent.injects(this)
    }

    override fun fetchNews(): Single<List<NewsInfo>> {
        if (BuildConfig.DEBUG) println("[Fetching news] Started")
        return newsService.fetchNews()
            .map { response: NewsResponse ->
                if (BuildConfig.DEBUG) println("[Fetching news] resultCode = ${response.resultCode}")
                response.news
            }
    }

    override fun fetchPost(id: Long): Single<NewsPost> {
        if (BuildConfig.DEBUG) println("[Fetching post] [id=$id] Started")
        return newsService.fetchPost(id)
            .map { response: PostResponse ->
                if (BuildConfig.DEBUG) println("[Fetching post] resultCode = ${response.resultCode}")
                response.post
            }
    }
}