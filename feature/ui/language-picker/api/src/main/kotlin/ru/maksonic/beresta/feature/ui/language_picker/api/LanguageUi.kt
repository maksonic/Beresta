package ru.maksonic.beresta.feature.ui.language_picker.api

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.language_engine.shell.provider.AppLangUi

/**
 * @Author maksonic on 15.10.2023
 */
data class LanguageUi(
    val id: Int,
    val language: AppLangUi,
    val hint: String = "Russian",
) {

    companion object {
        val Preview = LanguageUi(0, AppLangUi.RUSSIAN)
    }
    @Stable
    @Immutable
    data class Collection(val data: List<LanguageUi>) {
        companion object {
            val Initial = Collection(emptyList())

            val Preview = Collection(
                listOf(
                    LanguageUi(0, AppLangUi.RUSSIAN),
                    LanguageUi(1, AppLangUi.ENGLISH),
                    LanguageUi(2, AppLangUi.CHINESE),
                    LanguageUi(3, AppLangUi.CHINESE_TR),
                )
            )
        }
    }
}