package alektas.tinkoffnews.data.local

import alektas.tinkoffnews.data.entities.NewsInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_info")
    fun getNewsSource(): Observable<List<NewsInfo>>

    @Query("SELECT * FROM news_info LIMIT 1")
    fun getAnyInfo(): Maybe<NewsInfo?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<NewsInfo>)
}