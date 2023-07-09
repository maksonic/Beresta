package ru.maksonic.beresta.feature.language_picker.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 20.06.2023
 */
interface LanguagePickerApi {
    interface Feature {
        val current: Flow<AppLanguage>
        suspend fun setLanguage(lang: AppLanguage)
    }

    interface Ui {
        @Composable
        fun SheetContent(hideSheet: () -> Unit)
    }
}

