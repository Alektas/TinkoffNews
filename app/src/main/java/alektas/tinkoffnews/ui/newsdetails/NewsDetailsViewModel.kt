package alektas.tinkoffnews.ui.newsdetails

import alektas.tinkoffnews.App
import alektas.tinkoffnews.data.entities.NewsPost
import alektas.tinkoffnews.domain.Repository
import alektas.tinkoffnews.ui.ProcessState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsDetailsViewModel : ViewModel() {
    @Inject
    lateinit var repository: Repository
    var newsPost = MutableLiveData<NewsPost>()
    var loadingState = MutableLiveData<ProcessState>()
    private var disposable: Disposable? = null

    init {
        App.appComponent.injects(this)
    }

    fun fetchPost(id: Long)  {
        loadingState.value = ProcessState(ProcessState.STARTED)
        disposable = repository.getNewsPost(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsPost.value = it
                loadingState.value = ProcessState(ProcessState.SUCCESS)
            }, {
                loadingState.value = ProcessState(ProcessState.ERROR)
                it.printStackTrace()
            })
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}
