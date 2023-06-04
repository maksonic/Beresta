package ru.maksonic.beresta.feature.notes.list.api.domain

import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

/**
 * @Author maksonic on 11.04.2023
 */
interface DateFormatter {

    fun fetchFormattedUiDate(date: LocalDateTime, currentLang: AppLanguage): String

    class Core : DateFormatter {
        private companion object {
            object RU {
                const val TODAY = "сегодня"
                const val YESTERDAY = "вчера"
            }

            object US {
                const val TODAY = "today"
                const val YESTERDAY = "yesterday"
            }

            object CN {
                const val TODAY = "今天"
                const val YESTERDAY = "昨天"
            }

            private const val TIME_PATTERN = " - HH:mm"
            private const val YEAR_PATTERN = "yyyy"
            private const val NOT_SHOW_YEAR = ""
        }

        private val currentTime = LocalDateTime.now()
        private val currentCalendarDay = currentTime.dayOfWeek
        private val yesterday = currentTime.dayOfWeek.minus(1)
        private val currentYear = currentTime.year

        private fun getLiteralForLang(isToday: Boolean, lang: AppLanguage): String = when (lang) {
            AppLanguage.RUSSIAN -> if (isToday) RU.TODAY else RU.YESTERDAY
            AppLanguage.ENGLISH -> if (isToday) US.TODAY else US.YESTERDAY
            else -> if (isToday) CN.TODAY else CN.YESTERDAY
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
            val isToday = date.dayOfWeek == currentCalendarDay && currentYear == date.year
            val isYesterday = date.dayOfWeek == yesterday && currentYear == date.year
            val yearPattern = if (currentYear == date.year) NOT_SHOW_YEAR else YEAR_PATTERN
            val literal = getLiteralForLang(isToday, currentLang)

            return when {
                isToday or isYesterday -> buildUiDate(TIME_PATTERN, date, currentLang, literal)
                else -> buildUiDate("d MMMM $yearPattern - HH:mm", date, currentLang)
            }
        }
    }
}