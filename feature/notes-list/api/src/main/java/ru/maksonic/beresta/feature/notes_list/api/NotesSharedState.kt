package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 27.12.2022
 */
data class NotesSharedState(
    val isTopListScrollState: Boolean = true,
    val isVisibleFirstNote: Boolean = true,
    val backgroundColor: Color = Color.White
)