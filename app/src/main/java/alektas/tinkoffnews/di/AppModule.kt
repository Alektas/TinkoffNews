package alektas.tinkoffnews.di

import alektas.tinkoffnews.data.DataSource
import alektas.tinkoffnews.data.NewsRepository
import alektas.tinkoffnews.data.local.AppDatabase
import alektas.tinkoffnews.data.local.NewsDao
import alektas.tinkoffnews.data.remote.RemoteNewsSource
import alektas.tinkoffnews.domain.Repository
import alektas.tinkoffnews.services.NewsService
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val DATABASE_NAME = "tinkoff_news_db"

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return NewsRepository()
    }

    @Provides
    @Singleton
    fun provideNewsDao(db: AppDatabase): NewsDao {
        return db.getDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteNewsSource(): DataSource {
        return RemoteNewsSource()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tinkoff.ru/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}