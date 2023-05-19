package ru.maksonic.beresta.feature.search_bar.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState

/**
 * @Author maksonic on 21.02.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val notes: NoteUi.Collection = NoteUi.Collection.Empty,
    val searchList: NoteUi.Collection = NoteUi.Collection.Empty,
    val searchQuery: String = "",
    val barState: SearchBarState = SearchBarState.Collapsed,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCollapseSearchBarClicked : Ui()
        object OnExpandSearchBarClicked : Ui()
        object OnClearInputQueryClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedSearchBarState(val state: SearchBarState): Inner()
        data class UpdatedUserInputQueryChanged(val updatedQuery: String) : Inner()
        //    data class FetchedNotesCollection(val collection: NoteUi.Collection): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchLang : Cmd()
}

sealed class Eff : ElmEffect {
}