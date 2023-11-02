package ru.maksonic.beresta.feature.ui.search_bar.api

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBackplateState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel

/**
 * @Author maksonic on 02.07.2023
 */
@Stable
data class SearchBarUiState(
    val loadingState: ElmBaseModel,
    val notes: NoteUi.Collection,
    val searchList: NoteUi.Collection,
    val barState: SearchBarState,
    val backplateState: SearchBackplateState,
    val isVisibleGridIcon: Boolean,
    val counterValue: Int
) {
    companion object {
        val InitialMainNotes = SearchBarUiState(
            loadingState = ElmBaseModel.Initial,
            notes = NoteUi.Collection.Empty,
            searchList = NoteUi.Collection.Empty,
            barState = SearchBarState.Collapsed,
            backplateState = SearchBackplateState.MainNotes,
            isVisibleGridIcon = false,
            counterValue = 0
        )
        val InitialHiddenNotes = SearchBarUiState(
            loadingState = ElmBaseModel.Initial,
            notes = NoteUi.Collection.Empty,
            searchList = NoteUi.Collection.Empty,
            barState = SearchBarState.Collapsed,
            backplateState = SearchBackplateState.HiddenNotes,
            isVisibleGridIcon = true,
            counterValue = 0
        )
    }
}