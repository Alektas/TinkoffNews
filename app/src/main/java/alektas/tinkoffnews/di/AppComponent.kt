package alektas.tinkoffnews.di

import alektas.tinkoffnews.data.NewsRepository
import alektas.tinkoffnews.data.remote.RemoteNewsSource
import alektas.tinkoffnews.ui.NewsViewModel
import alektas.tinkoffnews.ui.newsdetails.NewsDetailsViewModel
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injects(repo: NewsRepository)
    fun injects(source: RemoteNewsSource)
    fun injects(viewModel: NewsViewModel)
    fun injects(viewModel: NewsDetailsViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder
        fun build(): AppComponent
    }
}