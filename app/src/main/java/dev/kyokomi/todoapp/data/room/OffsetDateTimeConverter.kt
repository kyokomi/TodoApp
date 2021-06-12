package dev.kyokomi.todoapp.data.room

import androidx.room.TypeConverter
import java.time.OffsetDateTime

class OffsetDateTimeConverter {

    @TypeConverter
    fun toDate(dateString: String?): OffsetDateTime? {
        return dateString?.let { OffsetDateTime.parse(it) }
    }

    @TypeConverter
    fun toDateString(date: OffsetDateTime?): String? {
        return date?.toString()
    }
}
