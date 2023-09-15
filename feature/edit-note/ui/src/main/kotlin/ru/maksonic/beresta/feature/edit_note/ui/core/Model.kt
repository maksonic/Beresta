package ru.maksonic.beresta.feature.edit_note.ui.core

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalDensity
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
enum class CurrentSheetContent {
    NOTHING, WALLPAPER_PICKER
}

@Stable
data class ModalSheet(
    val isVisible: Boolean,
    val content: CurrentSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            content = CurrentSheetContent.NOTHING
        )
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val modalSheet: ModalSheet,
    val isEntryPoint: Boolean,
    val isHiddenNote: Boolean,
    val isFetchedNote: Boolean,
    val editableNote: NoteUi,
    val folders: FolderUi.Collection,
    val selectedFolder: FolderUi?,
    val currentFolderId: Long,
    val isUserSelectedNoteFolder: Boolean,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            modalSheet = ModalSheet.Initial,
            isEntryPoint = false,
            isHiddenNote = false,
            isFetchedNote = false,
            editableNote = NoteUi.Default,
            selectedFolder = null,
            currentFolderId = 2L,
            folders = FolderUi.Collection.Empty,
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
        data class OnSelectNoteFolderClicked(val folder: FolderUi) : Ui()

        // Top bar actions
        data object OnPinClicked : Ui()
        data object OnAddNewChipClicked : Ui()
        data object OnChipFolderClicked : Ui()
        data object OnChangeColorMarkerClicked : Ui()
    }

    sealed class Inner : Msg() {
        data object FetchedPassedNote : Inner()
        data class FetchedPassedNoteResult(val isHidden: Boolean, val note: NoteUi) : Inner()
        data class UpdatedEntryPointValue(val isEntryPoint: Boolean) : Inner()
        data object ShowedKeyboardForExpandedFab : Inner()
        data class FetchedCurrentFolderId(val id: Long) : Inner()
        data class FetchedFoldersResult(val folders: List<FolderUi>) : Inner()
        data class UpdatedCurrentNoteTitle(val text: String) : Inner()
        data class UpdatedCurrentNoteMessage(val text: String) : Inner()
        data class UpdatedCurrentNoteMarkerColor(val colorId: Long) : Ui()

    }
}

sealed class Cmd : ElmCommand {
    data object FetchNote : Cmd()
    data object FetchFolders : Cmd()
    data class SaveNote(val note: NoteUi) : Cmd()
    data class UpdatePinnedNoteInCache(val note: NoteUi) : Cmd()
    data class UpdateNoteMarkerColor(val note: NoteUi) : Cmd()

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
    data class ShowMarkerColorPickerDialog(val colorId: Long) : Eff()
}