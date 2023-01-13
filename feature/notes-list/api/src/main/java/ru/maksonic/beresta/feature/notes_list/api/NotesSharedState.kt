package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.ui.graphics.Color

/**
 * @Author maksonic on 27.12.2022
 */
data class NotesSharedState(
    val isTopScrollState: Boolean = true,
    val isVisibleBottomPanel: Boolean = true,
    val backgroundColor: Color = Color.White
)