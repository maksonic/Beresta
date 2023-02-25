package ru.maksonic.beresta.feature.language_selector.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.feature.language_selector.api.provider.AppLanguage

/**
 * @Author maksonic on 16.02.2023
 */
interface LanguageSelectorApi {

    interface Lang {
        suspend fun setLanguage(lang: AppLanguage)
        val currentLanguage: Flow<AppLanguage>
    }

    interface Ui {
        @Composable
        fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit)
    }
}