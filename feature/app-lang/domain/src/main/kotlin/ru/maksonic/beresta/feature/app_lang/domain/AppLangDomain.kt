package ru.maksonic.beresta.feature.app_lang.domain

/**
 * @Author maksonic on 15.10.2023
 */
enum class AppLangDomain {
    RUSSIAN,
    ENGLISH,
    CHINESE,
    CHINESE_TR;

    override fun toString(): String = name
}