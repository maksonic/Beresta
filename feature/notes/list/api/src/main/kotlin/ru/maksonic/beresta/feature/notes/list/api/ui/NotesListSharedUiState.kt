package ru.maksonic.beresta.feature.notes.list.api.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.list.api.BaseBottomBarItem

/**
 * @Author maksonic on 29.03.2023
 */
data class NotesListSharedUiState(
    val isNotColoredTopBar: Boolean,
    val isVisibleBottomBar: Boolean,
    val isVisibleChipsRow: Boolean,
    val isSelectionState: Boolean,
    val isVisibleRemovedNotesSnackBar: Boolean,
    val removedCurrentNotesCount: Int,
    val passedToFolderNotes: List<NoteUi>,
    val isEnabledBottomBar: Boolean,
    val currentSortItemSelected: MutableState<SortedNotes>,
    val checkboxSortPinned: MutableState<Boolean>,
    val selectBarActions: Array<BaseBottomBarItem>,
    val onSnackBarClicked: () -> Unit,
    val onChangeGridCount: () -> Unit,

    ) {
    companion object {
        private val DefaultState = NotesListSharedUiState(
            isNotColoredTopBar = true,
            isVisibleBottomBar = true,
            isVisibleChipsRow = true,
            isSelectionState = false,
            selectBarActions = emptyArray(),
            onChangeGridCount = {},
            isVisibleRemovedNotesSnackBar = false,
            removedCurrentNotesCount = 0,
            onSnackBarClicked = {},
            passedToFolderNotes = emptyList(),
            isEnabledBottomBar = true,
            currentSortItemSelected = mutableStateOf(SortedNotes.DATE_CREATION_DESC),
            checkboxSortPinned = mutableStateOf(false)
        )
        val Initial = object : SharedUiState<NotesListSharedUiState>(DefaultState) {}
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NotesListSharedUiState

        if (isNotColoredTopBar != other.isNotColoredTopBar) return false
        if (isVisibleBottomBar != other.isVisibleBottomBar) return false
        if (isVisibleChipsRow != other.isVisibleChipsRow) return false
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
        var result = isNotColoredTopBar.hashCode()
        result = 31 * result + isVisibleBottomBar.hashCode()
        result = 31 * result + isVisibleChipsRow.hashCode()
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

fun SharedUiState<NotesListSharedUiState>.updateColoredTopBar(isColored: Boolean) =
    this.update { it.copy(isNotColoredTopBar = isColored) }

fun SharedUiState<NotesListSharedUiState>.updateChipsRowVisibility(isVisible: Boolean) =
    this.update { it.copy(isVisibleChipsRow = isVisible) }

fun SharedUiState<NotesListSharedUiState>.updateInitialSortState(
    currentSelection: SortedNotes,
    isSortPinned: Boolean
) {
    this.update {
        val currentItem = it.currentSortItemSelected.also { item -> item.value = currentSelection }
        val pinned = it.checkboxSortPinned.also { item -> item.value = isSortPinned }

        it.copy(currentSortItemSelected = currentItem, checkboxSortPinned = pinned)
    }
}