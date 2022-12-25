package ru.maksonic.beresta.feature.notes_list.ui.core

import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.NoteUi

/**
 * @Author maksonic on 25.12.2022
 */
object Feature {
    data class Model(
        val base: BaseModel,
        val notes: List<NoteUi> = emptyList(),
        val selectedNotes: MutableSet<Long> = mutableSetOf(),
        val errorMsg: String = "",
        val isSelectionState: Boolean = false,
    ) : ElmModel

    sealed class Msg : ElmMessage {
        sealed class Ui : Msg() {
            object RetryFetching : Msg()
            object RemoveSelectedItems : Ui()
            data class OnItemClicked(val noteId: Long) : Ui()
            data class SelectItemForRemove(val id: Long, val isSelected: Boolean): Ui()
            object SelectAllItems : Ui()
            data class OnItemLongPressed(val id: Long, val isSelected: Boolean, val isRemoving: Boolean) : Ui()
            object CancelNotesSelection : Ui()
        }

        sealed class Inner : Msg() {
            object Idle : Inner()
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