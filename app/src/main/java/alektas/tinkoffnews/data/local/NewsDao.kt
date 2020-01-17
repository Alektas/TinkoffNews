package alektas.tinkoffnews.data.local

import alektas.tinkoffnews.data.entities.NewsInfo
import alektas.tinkoffnews.data.entities.NewsPost
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_info ORDER BY publicationDate DESC")
    fun getNewsSource(): Observable<List<NewsInfo>>

    @Query("SELECT * FROM news_info LIMIT 1")
    fun getAnyInfo(): Maybe<NewsInfo?>

    @Query("SELECT * FROM news_posts WHERE id = :id LIMIT 1")
    fun getPost(id: Long): Maybe<NewsPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<NewsInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : NewsPost)
}