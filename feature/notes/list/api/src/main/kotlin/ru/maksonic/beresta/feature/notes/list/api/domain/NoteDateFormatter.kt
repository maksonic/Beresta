package ru.maksonic.beresta.feature.notes.list.api.domain

import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

/**
 * @Author maksonic on 11.04.2023
 */

interface NoteDateFormatter {

    fun fetchFormattedUiDate(date: LocalDateTime, currentLang: AppLanguage): String

    class Core : NoteDateFormatter {
        companion object {
            private const val TIME_PATTERN = " - HH:mm"
            private const val YEAR_PATTERN = "yyyy"
            private const val NOT_SHOW_YEAR = ""
        }

        private val currentTime = LocalDateTime.now()
        private val currentCalendarDay = currentTime.dayOfWeek.value
        private val yesterday = currentTime.dayOfWeek.value - 1
        private val currentYear = currentTime.year

        private fun getLiteralForLang(isToday: Boolean, lang: AppLanguage): String = when (lang) {
            AppLanguage.RUSSIAN -> if (isToday) "сегодня" else "вчера"
            AppLanguage.ENGLISH -> if (isToday) "today" else "yesterday"
            AppLanguage.CHINESE -> if (isToday) "今天" else "昨天"
            AppLanguage.CHINESE_TR -> if (isToday) "今天" else "昨天"
        }

        private fun buildUiDate(
            pattern: String,
            date: LocalDateTime,
            language: AppLanguage = AppLanguage.RUSSIAN,
            literal: String = ""
        ): String {

            val locale = when (language) {
                AppLanguage.RUSSIAN -> Locale("ru")
                AppLanguage.ENGLISH -> Locale.ENGLISH
                AppLanguage.CHINESE -> Locale.SIMPLIFIED_CHINESE
                AppLanguage.CHINESE_TR -> Locale.TRADITIONAL_CHINESE
                else -> Locale.ENGLISH
            }

            return DateTimeFormatterBuilder()
                .appendLiteral(literal)
                .appendPattern(pattern)
                .toFormatter()
                .withZone(ZoneId.systemDefault())
                .withLocale(locale)
                .format(date)
        }

        override fun fetchFormattedUiDate(date: LocalDateTime, currentLang: AppLanguage): String {
            val dayOfCreation = date.dayOfWeek.value
            val yearOfCreation = date.year
            val isToday = dayOfCreation == currentCalendarDay && currentYear == yearOfCreation
            val isYesterday = dayOfCreation == yesterday && currentYear == yearOfCreation
            val yearPattern = if (currentYear == yearOfCreation) NOT_SHOW_YEAR else YEAR_PATTERN
            val literal = getLiteralForLang(isToday, currentLang)

            return when {
                isToday or isYesterday -> buildUiDate(TIME_PATTERN, date, currentLang, literal)
                else -> buildUiDate("d MMMM $yearPattern - HH:mm", date, currentLang)
            }
        }
    }
}