package ru.maksonic.beresta.feature.folders_chips.api.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 15.07.2023
 */
val LocalCurrentSelectedFolderState = staticCompositionLocalOf<Long> {
    error("No current folder id provided")
}

object ChipFeature {
    val currentSelectedFolder: Long
        @Composable get() = LocalCurrentSelectedFolderState.current
}

fun SharedUiState<Long>.updateId(id: Long) = this.update(id)
