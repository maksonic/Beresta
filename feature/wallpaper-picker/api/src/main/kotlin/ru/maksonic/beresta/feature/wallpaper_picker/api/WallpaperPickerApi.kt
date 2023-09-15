package ru.maksonic.beresta.feature.wallpaper_picker.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 15.09.2023
 */
interface WallpaperPickerApi {
    interface Ui {
        @Composable
        fun SheetContent(hideSheet: () -> Unit)
    }
}