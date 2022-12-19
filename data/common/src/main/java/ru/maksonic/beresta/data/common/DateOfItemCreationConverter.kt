package ru.maksonic.beresta.data.common

import androidx.room.TypeConverter
import java.util.*

/**
 * @Author maksonic on 19.12.2022
 */
class DateOfItemCreationConverter {
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}