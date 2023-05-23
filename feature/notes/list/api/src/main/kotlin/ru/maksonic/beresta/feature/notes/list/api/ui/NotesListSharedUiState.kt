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
    val passedToFolderNotes: List<NoteUi>,
    val isEnabledBottomBar: Boolean,
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
            onSnackBarClicked = {},
            passedToFolderNotes = emptyList(),
            isEnabledBottomBar = true
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
        if (onChangeGridCount != other.onChangeGridCount) return false
        if (isVisibleRemovedNotesSnackBar != other.isVisibleRemovedNotesSnackBar) return false
        if (removedCurrentNotesCount != other.removedCurrentNotesCount) return false
        if (onSnackBarClicked != other.onSnackBarClicked) return false
        if (passedToFolderNotes != other.passedToFolderNotes) return false
        return isEnabledBottomBar == other.isEnabledBottomBar
    }

    override fun hashCode(): Int {
        var result = isVisibleFirstNote.hashCode()
        result = 31 * result + isVisibleFirstNoteOffset.hashCode()
        result = 31 * result + isScrollUp.hashCode()
        result = 31 * result + isSelectionState.hashCode()
        result = 31 * result + selectBarActions.contentHashCode()
        result = 31 * result + onChangeGridCount.hashCode()
        result = 31 * result + isVisibleRemovedNotesSnackBar.hashCode()
        result = 31 * result + removedCurrentNotesCount
        result = 31 * result + onSnackBarClicked.hashCode()
        result = 31 * result + passedToFolderNotes.hashCode()
        result = 31 * result + isEnabledBottomBar.hashCode()
        return result
    }

}

fun SharedUiState<NotesListSharedUiState>.updatePassedList(list: List<NoteUi>) =
    this.update { it.copy(passedToFolderNotes = list) }
