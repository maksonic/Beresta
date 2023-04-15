package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.State

/**
 * @Author maksonic on 29.03.2023
 */
data class NotesListSharedScrollState @OptIn(ExperimentalFoundationApi::class) constructor(
    val state: () -> LazyStaggeredGridState,
    val isVisibleFirstNote: State<Boolean>,
    val isVisibleFirstNoteOffset: State<Boolean>,
    val isScrollUp: State<Boolean>,
    val isSelectionState: State<Boolean>,
    val gridCellsCount: State<Int>,
)