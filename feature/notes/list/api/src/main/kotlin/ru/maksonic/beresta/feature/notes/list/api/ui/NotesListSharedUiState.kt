package ru.maksonic.beresta.feature.notes.list.api.ui

import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem

/**
 * @Author maksonic on 29.03.2023
 */
data class NotesListSharedUiState(
    val isVisibleFirstNote: Boolean,
    val isVisibleFirstNoteOffset: Boolean,
    val isScrollUp: Boolean,
    val isSelectionState: Boolean,
    val selectBarActions: Array<BaseBottomBarItem>,
    val onChangeGridCount: () -> Unit,
    val isVisibleRemovedNotesSnackBar: Boolean,
    val removedCurrentNotesCount: Int,
    val onSnackBarClicked: () -> Unit,
) {
    companion object {
        private val DefaultState = NotesListSharedUiState(
            isVisibleFirstNote = true,
            isVisibleFirstNoteOffset = true,
            isScrollUp = true,
            isSelectionState = false,
            selectBarActions = emptyArray(),
            onChangeGridCount = {},
            isVisibleRemovedNotesSnackBar = false,
            removedCurrentNotesCount = 0,
            onSnackBarClicked = {}
        )
        val Initial = object : SharedUiState<NotesListSharedUiState>(DefaultState) {}
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotesListSharedUiState

        if (isVisibleFirstNote != other.isVisibleFirstNote) return false
        if (isVisibleFirstNoteOffset != other.isVisibleFirstNoteOffset) return false
        if (isScrollUp != other.isScrollUp) return false
        if (isSelectionState != other.isSelectionState) return false
        if (!selectBarActions.contentEquals(other.selectBarActions)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isVisibleFirstNote.hashCode()
        result = 31 * result + isVisibleFirstNoteOffset.hashCode()
        result = 31 * result + isScrollUp.hashCode()
        result = 31 * result + isSelectionState.hashCode()
        result = 31 * result + selectBarActions.contentHashCode()
        return result
    }
}