package ru.maksonic.beresta.feature.app_lang.domain.formatter

import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import java.time.LocalDateTime

/**
 * @Author maksonic on 16.10.2023
 */
interface DateFormatter {
    fun fetchDateByLang(date: LocalDateTime, currentLang: AppLangDomain): String
}