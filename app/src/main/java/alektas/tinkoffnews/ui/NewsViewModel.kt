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
    var loadingState = MutableLiveData<ProcessState>()

    init {
        App.appComponent.injects(this)

        loadingState.value = ProcessState(ProcessState.STARTED)
        disposable = repository.observeNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<NewsInfo>>() {
                override fun onNext(news: List<NewsInfo>) {
                    newsLive.value = news
                    loadingState.value = ProcessState(ProcessState.SUCCESS)
                }

                override fun onComplete() {
                    loadingState.value = ProcessState(ProcessState.SUCCESS)
                }

                override fun onError(e: Throwable) {
                    loadingState.value = ProcessState(ProcessState.ERROR)
                    e.printStackTrace()
                }
            })
    }

    fun fetchNews() {
        loadingState.value = ProcessState(ProcessState.STARTED)
        repository.fetchNews()
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
