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
@Stable
data class Model(
    val base: BaseModel = BaseModel(),
    val notes: List<NoteUi> = emptyList(),
    val chipsNotesFilter: List<FilterChip> = FilterChipsCollection.Preview.chips,
    val isSelectionState: Boolean = false,
    val isVisibleRemoveAllNotesDialog: Boolean = false,
    val isRemoveWithoutRecoveryCheckboxState: Boolean = false,
    val errorMsg: String = "Неизвестная ошибка",
    val bottomPanelState: MutableSharedState<PanelSharedState>,
) : ElmModel


sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object RetryFetching : Ui()
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object SelectAll : Ui()
        object CancelSelection : Ui()
        object RemoveSelectedItems : Ui()
        data class ReplaceSelected(val selected: List<NoteUi>) : Ui()
        object PinSelected : Ui()
        data class OnSelectFilter(val index: Int) : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchingSuccess(val notes: List<NoteUi>) : Msg()
        data class FetchingError(val errorMsg: String) : Msg()
        data class SelectPanelVisibility(val isVisible: Boolean = false) : Msg()
        data class AfterRemoveResult(
            val notes: List<NoteUi>,
            val isAllSelected: Boolean,
        ) : Ui()
    }
}


sealed class Cmd : ElmCommand {
    object FetchData : Cmd()
    data class RemoveSelected(val notes: List<NoteUi>) : Cmd()
    object ListenBottomPanelActions : Cmd()
    data class PassPinNotesStateToBottomPanel(val isShowUnpinBtn: Boolean) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: List<NoteUi>): Cmd()
}

sealed class Eff : ElmEffect {
    data class ShowNoteForEdit(val id: Long) : Eff()
}