package ru.maksonic.beresta.screen.trash_list.notes.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
data class Model(
    val base: BaseModel,
    val notes: NoteUi.Collection,
    val selectedNotes: Set<NoteUi>,
    val isSelectionState: Boolean,
    val modalBottomSheetState: SheetState,
    val currentClickedNoteId: Long?,
    val isVisibleAcceptDeleteNotesDialog: Boolean,
    val isVisibleModalSheet: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            notes = NoteUi.Collection.Empty,
            selectedNotes = emptySet(),
            isSelectionState = false,
            modalBottomSheetState = SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = true
            ),
            currentClickedNoteId = null,
            isVisibleAcceptDeleteNotesDialog = false,
            isVisibleModalSheet = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnTrashedFoldersBtnClicked : Ui()
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object CancelSelectionState : Ui()
        object OnSelectAllNotesClicked : Ui()
        object HideModalBottomSheet : Ui()
        object OnRestoreFromTrashNoteClicked : Ui()
        object OnDeleteNoteClicked : Ui()
        object OnCancelDeleteDialogClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedRemovedNotesResult(val notes: List<NoteUi>) : Inner()
        data class FetchedError(val message: String) : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchRemovedData : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object NavigateToTrashedFoldersList : Eff()
    object HideModalSheet : Eff()
}