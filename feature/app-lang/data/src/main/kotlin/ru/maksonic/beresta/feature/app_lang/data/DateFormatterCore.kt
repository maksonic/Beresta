package ru.maksonic.beresta.feature.app_lang.data

import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.formatter.DateFormatter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

/**
 * @Author maksonic on 16.10.2023
 */
class DateFormatterCore : DateFormatter {
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

        private const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm"
        private const val TIME_PATTERN = " - HH:mm"
        private const val YEAR_PATTERN = "yyyy"
        private const val NOT_SHOW_YEAR = ""
    }

    private val currentTime = LocalDateTime.now()
    private val currentCalendarDay = currentTime.dayOfWeek
    private val yesterday = currentTime.dayOfWeek.minus(1)
    private val currentYear = currentTime.year

    private fun getLiteralForLang(isToday: Boolean, lang: AppLangDomain): String = when (lang) {
        AppLangDomain.RUSSIAN -> if (isToday) RU.TODAY else RU.YESTERDAY
        AppLangDomain.ENGLISH -> if (isToday) US.TODAY else US.YESTERDAY
        else -> if (isToday) CN.TODAY else CN.YESTERDAY
    }

    private fun buildUiDate(
        pattern: String,
        date: LocalDateTime,
        language: AppLangDomain = AppLangDomain.RUSSIAN,
        literal: String = ""
    ): String {

        val locale = when (language) {
            AppLangDomain.RUSSIAN -> Locale("ru")
            AppLangDomain.ENGLISH -> Locale.ENGLISH
            AppLangDomain.CHINESE -> Locale.SIMPLIFIED_CHINESE
            AppLangDomain.CHINESE_TR -> Locale.TRADITIONAL_CHINESE
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

    override fun fetchDateByLang(date: LocalDateTime, currentLang: AppLangDomain): String {
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