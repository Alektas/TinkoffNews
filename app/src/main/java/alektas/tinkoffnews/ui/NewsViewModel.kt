package alektas.tinkoffnews.ui

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.App
import alektas.tinkoffnews.domain.Repository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel : ViewModel() {
    @Inject
    lateinit var repository: Repository
    private var disposable: Disposable? = null
    val newsLive = MutableLiveData<List<NewsInfo>>()

    init {
        App.appComponent.injects(this)

        fetchNews()

        disposable = repository.observeNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<NewsInfo>>() {
                override fun onNext(news: List<NewsInfo>) {
                    newsLive.value = news
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun fetchNews() {
        repository.fetchNews()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}