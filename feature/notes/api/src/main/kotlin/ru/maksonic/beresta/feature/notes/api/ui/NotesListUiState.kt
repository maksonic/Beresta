package ru.maksonic.beresta.feature.notes.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel

/**
 * @Author maksonic on 01.07.2023
 */
@Stable
@Immutable
data class NotesListUiState(
    val state: ElmBaseModel,
    val collection: NoteUi.Collection,
    val selectedList: Set<NoteUi>,
    val removedList: Set<NoteUi>,
    val isSelection: Boolean,
    val isVisibleUnpinMainBarIcon: Boolean,
    val isVisibleRemovedSnackBar: Boolean,
) {
    companion object {
        val Initial = NotesListUiState(
            state = ElmBaseModel.Loading,
            collection = NoteUi.Collection.Empty,
            selectedList = emptySet(),
            removedList = emptySet(),
            isSelection = false,
            isVisibleUnpinMainBarIcon = false,
            isVisibleRemovedSnackBar = false
        )
    }
}