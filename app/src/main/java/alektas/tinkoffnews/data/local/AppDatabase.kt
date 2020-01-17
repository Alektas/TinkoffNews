package alektas.tinkoffnews.data.local

import alektas.tinkoffnews.data.entities.NewsInfo
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsInfo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): NewsDao
}