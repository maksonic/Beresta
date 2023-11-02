package ru.maksonic.beresta.feature.ui.language_picker.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguagePickerUiApi

/**
 * @Author maksonic on 15.10.2023
 */
class LanguagePickerUiCore : LanguagePickerUiApi {
    @Composable
    override fun SheetContent() {
        Container()
    }
}