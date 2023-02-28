package ru.maksonic.beresta.feature.theme_selector.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 28.02.2023
 */
interface ThemeSelectorUiApi {
    @Composable
    fun BottomSheet(isVisibleSheet: Boolean, hideSheet: () -> Unit)
}