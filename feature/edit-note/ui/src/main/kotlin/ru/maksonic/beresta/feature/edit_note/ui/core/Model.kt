package ru.maksonic.beresta.feature.edit_note.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi

/**
 * @Author maksonic on 26.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val isEntryPoint: Boolean,
    val isHiddenNote: Boolean,
    val currentNote: NoteUi,
    val folders: FolderUi.Collection,
    val selectedFolder: FolderUi?,
    val currentFolderId: Long,
    val isUserSelectedNoteFolder: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            isEntryPoint = false,
            isHiddenNote = false,
            currentNote = NoteUi.Default,
            folders = FolderUi.Collection.Empty,
            selectedFolder = null,
            currentFolderId = 2L,
            isUserSelectedNoteFolder = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnSaveNoteClicked(val isHiddenNote: Boolean) : Ui()
        data object OnTopBarBackPressed : Ui()

        //Idle BottomBar actions
        data object OnStartRecordVoiceClicked : Ui()
        data object OnAddImagesClicked : Ui()
        data object OnAddCameraSnapshotClicked : Ui()
        data object OnSetNoteWallpaperClicked : Ui()
        data class OnSelectNoteFolderClicked(val folderId: Long) : Ui()

        // Top bar actions
        data object OnPinClicked : Ui()
        data object OnAddNewChipClicked : Ui()
        data object OnChipFolderClicked : Ui()
        data object OnChangeColorMarkerClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class CheckedEntryPoint(val value: Boolean) : Inner()
        data object ShowedKeyboardForExpandedFab : Inner()
        data class FetchedPassedNoteResult(
            val isHidden: Boolean,
            val note: NoteUi,
            val folder: FolderUi
        ) : Inner()

        data class FetchedFoldersResult(val folders: List<FolderUi>) : Inner()
        data class UpdatedCurrentNoteTitle(val text: String) : Inner()
        data class UpdatedCurrentNoteMessage(val text: String) : Inner()
        data class FetchedCurrentFolderId(val id: Long) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNote : Cmd()
    data object FetchFolders : Cmd()
    data class SaveNote(val note: NoteUi) : Cmd()
    data class UpdatePinnedNoteInCache(val note: NoteUi) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object ShowToastMaxLengthNoteExceed : Eff()
    data object ShowNoteUpdateSnackBar : Eff()
    data object ShowKeyboardForExpandedFab : Eff()
    data object HideKeyboard : Eff()
    data object ShowAddNewChipDialog : Eff()
    data object CollapseFab : Eff()
    data class UpdateCurrentFolder(val id: Long) : Eff()
    data object ShowMarkerColorPickerDialog : Eff()
}