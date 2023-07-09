package ru.maksonic.beresta.feature.language_picker.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi

/**
 * @Author maksonic on 20.06.2023
 */
class LanguagePickerSheetContent : LanguagePickerApi.Ui {
    @Composable
    override fun SheetContent(hideSheet: () -> Unit) {
        Container(hideSheet)
    }
}
