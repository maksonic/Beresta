package ru.maksonic.beresta.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

/**
 * @Author maksonic on 19.12.2022
 */
class LocalDateTimeConverter {
    @TypeConverter
    fun dateToStamp(date: LocalDateTime): String = date.toString()

    @TypeConverter
    fun stampToInstant(stamp: String): LocalDateTime = LocalDateTime.parse(stamp)
}