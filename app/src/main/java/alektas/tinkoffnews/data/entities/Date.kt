package alektas.tinkoffnews.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date

class Date {
    @SerializedName("milliseconds")
    @Expose
    var milliseconds: Long? = null

    override fun toString(): String {
        milliseconds?.let {
            val date = Date(it)
            val formatter = SimpleDateFormat.getDateInstance()
            return formatter.format(date)
        }
        return super.toString()
    }
}