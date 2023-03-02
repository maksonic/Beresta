package ru.maksonic.beresta.feature.search_bar.core.presentation

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val notes: NoteUi.Collection = NoteUi.Collection.Empty,
    val searchList: NoteUi.Collection = NoteUi.Collection.Empty,
    val searchQuery: String = "",
    val isExpandedBar: Boolean = false,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCollapseSearchBarClicked : Ui()
        object OnExpandSearchBarClicked : Ui()
        object OnClearInputQueryClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class AfterUserInputQueryChanged(val updatedQuery: String): Inner()
        data class FetchedNotesCollection(val collection: NoteUi.Collection): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchData : Cmd()
}

sealed class Eff : ElmEffect {
}