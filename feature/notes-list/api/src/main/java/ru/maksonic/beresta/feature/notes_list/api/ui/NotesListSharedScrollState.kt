package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState

/**
 * @Author maksonic on 29.03.2023
 */
data class NotesListSharedScrollState @OptIn(ExperimentalFoundationApi::class) constructor(
    val state: () -> LazyStaggeredGridState,
    val isVisibleFirstNote: () -> Boolean,
    val isVisibleFirstNoteOffset: () -> Boolean,
    val isScrollUp: () -> Boolean,
    val isSelectionState: () -> Boolean,
    val gridCellsCount: () -> Int,
)