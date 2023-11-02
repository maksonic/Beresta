package ru.maksonic.beresta.feature.notes_list.ui.api.list

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel

/**
 * @Author maksonic on 01.07.2023
 */
@Stable
@Immutable
data class NotesListUiState(
    val state: ElmBaseModel,
    val collection: NoteUi.Collection,
    val removedList: List<NoteUi>,
    val isSelection: Boolean,
    val isVisibleUnpinBottomBarIcon: Boolean,
    val isHidden: Boolean
) {
    companion object {
        val Initial = NotesListUiState(
            state = ElmBaseModel.Loading,
            collection = NoteUi.Collection.Empty,
            removedList = emptyList(),
            isSelection = false,
            isVisibleUnpinBottomBarIcon = false,
            isHidden = false
        )
    }
}