package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.FilterChip
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.collection.FilterChipsCollection

/**
 * @Author maksonic on 25.12.2022
 */
object Feature {

    @Stable
    data class Model(
        val base: BaseModel,
        val notes: List<NoteUi> = emptyList(),
        val chipsNotesFilter: List<FilterChip> = FilterChipsCollection.Preview.chips,
        val isSelectionState: Boolean = false,
        val errorMsg: String = "",
    ) : ElmModel

    sealed class Msg : ElmMessage {
        sealed class Ui : Msg() {
            object RetryFetching : Msg()
            object RemoveSelectedItems : Ui()
            data class OnNoteClicked(val id: Long) : Ui()
            data class OnNoteLongClicked(val id: Long) : Ui()
            object SelectAllItems : Ui()
            object CancelNotesSelection : Ui()
            data class OnSelectNotesFilter(val index: Int) : Ui()
        }

        sealed class Inner : Msg() {
            data class FetchingSuccess(val notes: List<NoteUi>) : Inner()
            data class FetchingError(val errorMsg: String) : Inner()
        }
    }

    sealed class Cmd : ElmCommand {
        object FetchData : Cmd()
    }

    sealed class Eff : ElmEffect {
        object ShowNoteForEdit : Eff()
    }
}