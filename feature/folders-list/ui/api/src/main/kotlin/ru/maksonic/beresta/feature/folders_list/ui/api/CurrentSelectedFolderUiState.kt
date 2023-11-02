package ru.maksonic.beresta.feature.folders_list.ui.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 15.07.2023
 */
val LocalCurrentSelectedFolderState = staticCompositionLocalOf<Long> {
    error("No current folder id provided")
}

object FoldersFeature {
    val currentSelected: Long
        @Composable get() = LocalCurrentSelectedFolderState.current
}