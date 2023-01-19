package ru.maksonic.beresta.feature.notes_list.ui.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.core.MutableSharedState
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.botom_panel.api.PanelSharedState
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
        val selectedCount: Int = 0,
        val errorMsg: String = "",
        val bottomPanelState: MutableSharedState<PanelSharedState>,
    ) : ElmModel

    sealed class Msg : ElmMessage {
        sealed class Ui : Msg() {
            object RetryFetching : Msg()
            object RemoveSelectedItems : Ui()
            data class OnNoteClicked(val id: Long) : Ui()
            data class OnNoteLongClicked(val id: Long) : Ui()
            object SelectAllNotes : Ui()
            object CancelNotesSelection : Ui()
            data class OnSelectNotesFilter(val index: Int) : Ui()
        }

        sealed class Inner : Msg() {
            data class FetchingSuccess(val notes: List<NoteUi>) : Inner()
            data class FetchingError(val errorMsg: String) : Inner()
            data class SelectPanelVisibility(val isVisible: Boolean = false) : Inner()
        }
    }

    sealed class Cmd : ElmCommand {
        object FetchData : Cmd()
        object ListenBottomPanelActions : Cmd()
    }

    sealed class Eff : ElmEffect {
        object ShowNoteForEdit : Eff()
    }
}