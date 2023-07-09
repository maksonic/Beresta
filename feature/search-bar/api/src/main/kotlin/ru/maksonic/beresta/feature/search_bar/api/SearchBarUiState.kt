package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi

/**
 * @Author maksonic on 02.07.2023
 */
@Stable
data class SearchBarUiState(
    val state: ElmBaseModel,
    val notes: NoteUi.Collection,
    val searchList: NoteUi.Collection,
    val barState: SearchBarState,
) {
    companion object {
        val Initial = SearchBarUiState(
            state = ElmBaseModel.Initial,
            notes = NoteUi.Collection.Empty,
            searchList = NoteUi.Collection.Empty,
            barState = SearchBarState.Collapsed,
        )
    }
}