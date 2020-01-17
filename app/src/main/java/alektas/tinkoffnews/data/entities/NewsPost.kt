package alektas.tinkoffnews.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsPost {
    @SerializedName("title")
    @Expose
    var info: NewsInfo? = null
    @SerializedName("content")
    @Expose
    var body: String? = null
    @SerializedName("creationDate")
    @Expose
    val creationDate: Date? = null
    @SerializedName("lastModificationDate")
    @Expose
    val lastModificationDate: Date? = null

    override fun toString(): String {
        return "NewsPost(id=${info?.id}, name=${info?.name})"
    }
}