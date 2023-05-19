package ru.maksonic.beresta.feature.language_picker.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 24.04.2023
 */
interface LanguagePickerApi {
    interface Ui {
        @Composable
        fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit)
    }
}