package alektas.tinkoffnews

import alektas.tinkoffnews.di.AppComponent
import alektas.tinkoffnews.di.DaggerAppComponent
import android.app.Application

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}