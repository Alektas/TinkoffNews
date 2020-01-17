package alektas.tinkoffnews.data.entities

import alektas.tinkoffnews.data.converters.DateConverter
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_info",
    indices = [Index(value = ["id"], unique = true)]
)
class NewsInfo {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("text")
    @Expose
    var body: String? = null
    @TypeConverters(DateConverter::class)
    @SerializedName("publicationDate")
    @Expose
    var publicationDate: Date? = null

    override fun toString(): String {
        return "NewsInfo(id=$id, name=$name, publicationDate=$publicationDate)"
    }
}