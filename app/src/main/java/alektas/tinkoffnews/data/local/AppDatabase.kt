package alektas.tinkoffnews.data.local

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.data.entities.NewsPost
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsInfo::class, NewsPost::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): NewsDao
}