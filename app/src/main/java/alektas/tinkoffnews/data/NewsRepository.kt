package alektas.tinkoffnews.data

import alektas.tinkoffnews.App
import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.data.entities.NewsPost
import alektas.tinkoffnews.data.local.NewsDao
import alektas.tinkoffnews.domain.Repository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository: Repository {
    @Inject
    lateinit var remoteSource: DataSource
    @Inject
    lateinit var newsDao: NewsDao
    private val newsSource: Observable<List<NewsInfo>>

    init {
        App.appComponent.injects(this)
        newsSource = newsDao.getNewsSource()
    }

    override fun observeNews(): Observable<List<NewsInfo>> {
        return newsSource
    }

    override fun fetchNews() {
        remoteSource.fetchNews()
            .subscribeOn(Schedulers.io())
            .subscribe({
//                newsSource.onNext(it)
                newsDao.insert(it)
            }, {
                it.printStackTrace()
            })
    }

    override fun fetchNewsPost(id: Long): Single<NewsPost> {
        return remoteSource.fetchPost(id)
    }

}