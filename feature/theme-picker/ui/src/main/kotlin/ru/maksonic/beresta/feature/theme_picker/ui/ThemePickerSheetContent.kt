package ru.maksonic.beresta.feature.theme_picker.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi

/**
 * @Author maksonic on 19.06.2023
 */
class ThemePickerSheetContent : ThemePickerApi.Ui {
    @Composable
    override fun SheetContent(hideSheet: () -> Unit) {
        Container(hideSheet)
    }
}

