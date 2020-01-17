package alektas.tinkoffnews.di

import alektas.tinkoffnews.data.remote.RemoteNewsSource
import alektas.tinkoffnews.domain.Repository
import alektas.tinkoffnews.ui.NewsViewModel
import alektas.tinkoffnews.ui.newsdetails.NewsDetailsViewModel
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injects(repo: Repository)
    fun injects(source: RemoteNewsSource)
    fun injects(viewModel: NewsViewModel)
    fun injects(viewModel: NewsDetailsViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}