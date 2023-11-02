package ru.maksonic.beresta.feature.app_lang.domain

/**
 * @Author maksonic on 15.10.2023
 */
data class LanguageDomain(
    val id: Int,
    val language: AppLangDomain,
    val hint: String = "",
)