package ru.maksonic.beresta.feature.ui.edit_note.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiState
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 26.04.2023
 */
enum class CurrentSheetContent { NOTHING }

@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
    val content: CurrentSheetContent
) {
    companion object {
        val Initial = ModalSheet(
            isVisible = false,
            skipPartiallyExpanded = false,
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
    val isFetchedFolders: Boolean,
    val editableNote: NoteUi,
    val folders: FolderUi.Collection,
    val selectedFolder: FolderUi?,
    val currentFolderId: Long,
    val isUserSelectedNoteFolder: Boolean,
    val isPinNoteSelected: Boolean,
    val isVisibleAddFolderDialog: Boolean,
    val isVisibleWallpaperPickerSheet: Boolean,
    val markerState: MarkerPickerUiState,
    val currentWallpaper: BaseWallpaper<Color>
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            modalSheet = ModalSheet.Initial,
            isEntryPoint = false,
            isHiddenNote = false,
            isFetchedNote = false,
            isFetchedFolders = false,
            editableNote = NoteUi.Default,
            folders = FolderUi.Collection.Empty,
            selectedFolder = null,
            currentFolderId = 2L,
            isUserSelectedNoteFolder = false,
            isPinNoteSelected = false,
            isVisibleAddFolderDialog = false,
            isVisibleWallpaperPickerSheet = false,
            markerState = MarkerPickerUiState.Initial,
            currentWallpaper = BaseWallpaper.empty()
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnSaveNoteClicked(val isHiddenNote: Boolean) : Ui()
        data class OnSelectNoteFolderClicked(val folder: FolderUi) : Ui()
        data object OnTopBarBackPressed : Ui()
        data object OnHideModalBottomSheet : Ui()

        //Idle BottomBar actions
        data object OnStartRecordVoiceClicked : Ui()
        data object OnAddImagesClicked : Ui()
        data object OnAddCameraSnapshotClicked : Ui()
        // data class OnSelectNoteFolderClicked(val folder: FolderUi) : Ui()

        // Top bar actions
        data object OnPinClicked : Ui()
        data object OnAddNewFolderClicked : Ui()
        data object OnCloseAddFolderDialogClicked : Ui()
        data object OnChipFolderClicked : Ui()
        data object OnSelectColorMarkerClicked : Ui()
        data object HiddenMarkerColorPickerDialog : Ui()
        data class UpdatedWallpaperPickerSheetVisibility(val isVisible: Boolean) : Ui()
    }

    sealed class Inner : Msg() {
        data object FetchedPassedNote : Inner()
        data class FetchedPassedNoteResult(
            val isHidden: Boolean,
            val note: NoteUi,
            val markerState: MarkerPickerUiState,
            val wallpaper: BaseWallpaper<Color>
        ) : Inner()

        data class FetchedCurrentFolderId(val id: Long) : Inner()
        data class FetchedFoldersSuccess(val folders: List<FolderUi>) : Inner()
        data class UpdatedEntryPointValue(val isEntryPoint: Boolean) : Inner()
        data class ShowedKeyboardForExpandedFab(
            val isVisibleWallpaperPickerSheet: Boolean
        ) : Inner()

        data class UpdatedCurrentNoteTitle(val text: String) : Inner()
        data class UpdatedCurrentNoteMessage(val text: String) : Inner()
        data class UpdatedCurrentNoteMarkerColor(val colorId: Long) : Inner()
        data object HiddenModalBottomSheet : Inner()
        data class UpdatedNoteWallpaper(val wallpaper: BaseWallpaper<Color>) : Inner()
        data class FetchedNoteWallpaperResult(val value: BaseWallpaper<Color>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchNote : Cmd()
    data object FetchFolders : Cmd()
    data class FetchWallpaper(val wallpaper: BaseWallpaper<Color>) : Cmd()
    data class SaveNote(val note: NoteUi, val wallpaper: BaseWallpaper<Color>) : Cmd()
    data class UpdateNoteMarkerColor(val note: NoteUi) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object ShowModalSheet : Eff()
    data object HideModalSheet : Eff()
    data object ShowToastMaxLengthNoteExceed : Eff()
    data object ShowNoteUpdateSnackBar : Eff()
    data object ShowKeyboardForExpandedFab : Eff()
    data object HideKeyboard : Eff()
    data object ShowAddNewChipDialog : Eff()
    data object CollapseFab : Eff()
    data class UpdateCurrentFolder(val id: Long) : Eff()
    data class ShowMarkerColorPickerDialog(val colorId: Long) : Eff()
}