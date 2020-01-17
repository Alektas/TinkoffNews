package alektas.tinkoffnews.data.converters

import alektas.tinkoffnews.data.entities.Date
import androidx.room.TypeConverter

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.milliseconds ?: 0
    }

    @TypeConverter
    fun toDate(millis: Long): Date {
        return Date().apply { milliseconds = millis }
    }
}