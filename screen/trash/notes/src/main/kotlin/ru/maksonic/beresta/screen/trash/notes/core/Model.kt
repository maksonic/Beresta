package ru.maksonic.beresta.screen.trash.notes.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 30.05.2023
 */
@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
) {
    companion object {
        val Initial = ModalSheet(isVisible = false, skipPartiallyExpanded = true)
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val notes: NoteUi.Collection,
    val isSelection: Boolean,
    val currentClickedNoteId: Long?,
    val isVisibleAcceptDeleteNotesDialog: Boolean,
    val isVisibleModalSheet: Boolean,
    val isSingleSelection: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            modalSheet = ModalSheet.Initial,
            notes = NoteUi.Collection.Empty,
            isSelection = false,
            currentClickedNoteId = null,
            isVisibleAcceptDeleteNotesDialog = false,
            isVisibleModalSheet = false,
            isSingleSelection = true
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnRetryFetchDataClicked : Ui()
        data object OnTopBarBackPressed : Ui()
        data object OnTrashedFoldersBtnClicked : Ui()
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        data object CancelSelectionState : Ui()
        data object OnSelectAllNotesClicked : Ui()
        data object HideModalBottomSheet : Ui()
        data object OnModalSheetRestoreClicked : Ui()
        data object OnModalSheetDeleteClicked : Ui()
        data object OnBottomBarRestoreSelectedNotesClicked : Ui()
        data object OnBottomBarDeleteSelectedNotesClicked : Ui()
        data object OnAcceptDeleteWarningDialogClicked : Ui()
        data object OnCancelDeleteWarningDialogClicked : Ui()
        data object OnDeleteAllNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedDataResult(val notes: List<NoteUi>) : Inner()
        data class FetchedDataFail(val message: String = "Error") : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchData : Cmd()
    data object RetryFetchData : Cmd()
    data class DeleteNotes(val notes: List<NoteUi>) : Cmd()
    data class RestoreNotes(val notes: List<NoteUi>) : Cmd()}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object NavigateToTrashedFoldersList : Eff()
    data object HideModalSheet : Eff()
}