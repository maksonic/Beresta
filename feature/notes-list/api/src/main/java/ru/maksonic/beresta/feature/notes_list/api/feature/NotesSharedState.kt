package ru.maksonic.beresta.feature.notes_list.api.feature

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * @Author maksonic on 27.12.2022
 */
data class NotesSharedState(
    val isShowMainToolbar: Boolean = true,
    val isShowBottomPanel: Boolean = true,
    val isColoredTopBar: Boolean = true
)

fun MutableStateFlow<NotesSharedState>.isVisibleBottomPanel(isVisible: Boolean) {
    this.update { update ->
        update.copy(isShowBottomPanel = isVisible)
    }
}

fun MutableStateFlow<NotesSharedState>.isVisibleMainTopBar(isVisible: Boolean) {
    this.update { update ->
        update.copy(isShowMainToolbar = isVisible)
    }
}

fun MutableStateFlow<NotesSharedState>.isColoredMainTopBar(isColored: Boolean) {
    this.update { update ->
        update.copy(isColoredTopBar = isColored)
    }
}