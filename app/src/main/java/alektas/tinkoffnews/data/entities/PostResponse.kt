package alektas.tinkoffnews.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostResponse {
    @SerializedName("trackingId")
    @Expose
    var trackingId: String? = null
    @SerializedName("resultCode")
    @Expose
    var resultCode: String? = null
    @SerializedName("payload")
    @Expose
    var post: NewsPost? = null

    override fun toString(): String {
        return "NewsResponse(trackingId=$trackingId, resultCode=$resultCode, post=$post)"
    }

}