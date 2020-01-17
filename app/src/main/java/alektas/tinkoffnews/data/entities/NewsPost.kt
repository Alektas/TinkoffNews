package alektas.tinkoffnews.data.entities

import alektas.tinkoffnews.data.converters.DateConverter
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_posts",
    indices = [Index(value = ["id"], unique = true)],
    foreignKeys = [ForeignKey(entity = NewsInfo::class, parentColumns = ["id"], childColumns = ["id"])],
    primaryKeys = ["id"]
)
class NewsPost {
    @Embedded
    @SerializedName("title")
    @Expose
    var info: NewsInfo? = null
    @SerializedName("content")
    @Expose
    var body: String? = null
    @TypeConverters(DateConverter::class)
    @SerializedName("creationDate")
    @Expose
    var creationDate: Date? = null
    @TypeConverters(DateConverter::class)
    @SerializedName("lastModificationDate")
    @Expose
    var lastModificationDate: Date? = null

    override fun toString(): String {
        return "NewsPost(id=${info?.id}, name=${info?.name})"
    }
}