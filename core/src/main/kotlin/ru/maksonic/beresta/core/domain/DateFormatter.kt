package ru.maksonic.beresta.core.domain

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * @Author maksonic on 21.02.2023
 */
/*object DateFormatter {
    private const val TODAY_PATTERN = "сегодня - HH:mm"
    private const val YESTERDAY_PATTERN = "вчера - HH:mm"
    private const val YEAR_PATTERN = "yyyy"
    private const val NOT_SHOW_YEAR = ""
    private val instant = Instant.now()
    private val currentCalendarDay = instant.atZone(ZoneId.systemDefault()).dayOfWeek.value
    private val yesterday = instant.atZone(ZoneId.systemDefault()).dayOfWeek.value - 1
    private val currentYear = formatDate(YEAR_PATTERN, instant)

    fun fetchFormattedDate(date: Instant): String {
        val creationDay = date.atZone(ZoneId.systemDefault()).dayOfWeek.value
        val creationYear = formatDate(YEAR_PATTERN, date)
        val today = creationDay == currentCalendarDay && currentYear == creationYear
        val yesterday = creationDay == yesterday && currentYear == creationYear
        val year = if (currentYear == creationYear) NOT_SHOW_YEAR else creationYear

        return when {
            today -> formatDate(TODAY_PATTERN, date)
            yesterday -> formatDate(YESTERDAY_PATTERN, date)
            else -> formatDate("d MMMM $year - HH:mm", date)
        }
    }

    private fun formatDate(pattern: String, instant: Instant) =
        DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(instant)
}*/