package alektas.tinkoffnews.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Date {
    @SerializedName("milliseconds")
    @Expose
    val milliseconds: Long? = null
}