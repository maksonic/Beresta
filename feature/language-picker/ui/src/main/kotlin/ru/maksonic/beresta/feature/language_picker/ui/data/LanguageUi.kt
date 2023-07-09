package ru.maksonic.beresta.feature.language_picker.ui.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 22.04.2023
 */
data class LanguageUi(
    val id: Int,
    val language: AppLanguage,
    val hint: String = "",
) {

    companion object {
        val Preview = LanguageUi(0, AppLanguage.RUSSIAN)
    }
    @Stable
    @Immutable
    data class Collection(val data: List<LanguageUi>) {
        companion object {
            val Preview = Collection(
                listOf(
                    LanguageUi(0, AppLanguage.RUSSIAN),
                    LanguageUi(1, AppLanguage.ENGLISH),
                    LanguageUi(2, AppLanguage.CHINESE),
                    LanguageUi(3, AppLanguage.CHINESE_TR),
                )
            )
        }
    }
}