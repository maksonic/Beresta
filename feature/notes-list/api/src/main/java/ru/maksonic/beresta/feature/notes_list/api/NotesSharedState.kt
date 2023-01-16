package ru.maksonic.beresta.feature.notes_list.api

/**
 * @Author maksonic on 27.12.2022
 */
data class NotesSharedState(
    val isShowMainToolbar: Boolean = true,
    val isShowBottomPanel: Boolean = true,
    val isColoredTopBar: Boolean = true
)