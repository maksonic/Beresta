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
    val base: BaseModel,
    val notes: NoteUi.Collection,
    val searchList: NoteUi.Collection,
    val searchQuery: String,
    val barState: SearchBarState,
) : ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            notes = NoteUi.Collection.Empty,
            searchList = NoteUi.Collection.Empty,
            searchQuery = "",
            barState = SearchBarState.Collapsed,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCollapseSearchBarClicked : Ui()
        object OnExpandSearchBarClicked : Ui()
        object OnClearInputQueryClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedSelectedBarState(val isSelected: Boolean) : Inner()
        data class UpdatedUserInputQueryChanged(val updatedQuery: String) : Inner()
        data class FetchedResultData(val notes: NoteUi.Collection) : Inner()
        data class FetchedResultError(val errorMsg: String = "") : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNotesList : Cmd()
}

sealed class Eff : ElmEffect {
    data class UpdateSharedBarState(val state: SearchBarState) : Eff()
}