package ru.maksonic.beresta.base_domain

import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author maksonic on 20.12.2022
 */
object DateFormatter {
    private const val TODAY_PATTERN = "сегодня - HH:mm"
    private const val TODAY_SHORT_PATTERN = "d MMMM HH:mm"
    private const val YESTERDAY_PATTERN = "вчера - HH:mm"
    private const val YEAR_PATTERN = "yyyy"
    private const val NOT_SHOW_YEAR = ""
    private val calendar = Calendar.getInstance()
    private val currentCalendarDay = calendar.get(Calendar.DATE)
    private val currentYear = SimpleDateFormat(YEAR_PATTERN).format(calendar.time)
    private val yesterday = with(Calendar.getInstance()) {
        add(Calendar.DATE, -1)
        return@with this.get(Calendar.DATE)
    }

    // TODO: FIX - [SimpleDateFormat]
    fun fetchFormattedDate(date: Calendar): String {
        val creationDay = date.get(Calendar.DATE)
        val creationYear = SimpleDateFormat(YEAR_PATTERN).format(date.time)
        val today = creationDay == currentCalendarDay && currentYear == creationYear
        val yesterday = creationDay == yesterday && currentYear == creationYear
        return when {
            today -> SimpleDateFormat(TODAY_PATTERN).format(date.time)
            yesterday -> SimpleDateFormat(YESTERDAY_PATTERN).format(date.time)
            else -> {
                val year = if (currentYear == creationYear) NOT_SHOW_YEAR else creationYear
                SimpleDateFormat("d MMMM $year - HH:mm").format(date.time)
            }
        }
    }

    fun fetchCurrentTimeWithShortDate(calendar: Calendar): String =
        SimpleDateFormat(TODAY_SHORT_PATTERN).format(calendar.time)
}