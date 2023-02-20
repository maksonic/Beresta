package ru.maksonic.beresta.feature.language_selector.api

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage

/**
 * @Author maksonic on 20.02.2023
 */
data class LanguageUi(
    val id: Int,
    val language: AppLanguage,
    val hint: String = "",
    val isSelected: Boolean = false
)

@Stable
@Immutable
data class LanguagesCollection(val data: List<LanguageUi>)