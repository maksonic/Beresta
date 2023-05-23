package ru.maksonic.beresta.feature.edit_note.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 26.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: BaseModel,
    val isEntryPoint: Boolean,
    val isExpandedFab: Boolean,
    val currentNote: NoteUi,
    val currentSelectedFolderId: Long,
    ) : ElmModel {
    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            isEntryPoint = false,
            isExpandedFab = false,
            currentNote = NoteUi.Default,
            currentSelectedFolderId = 0L
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnSaveNoteClicked : Ui()
        object OnTopBarBackPressed : Ui()
        object OnExpandFabClicked : Ui()
        //Idle BottomBar actions
        object OnStartRecordVoiceClicked : Ui()
        object OnAddImagesClicked : Ui()
        object OnAddCameraSnapshotClicked : Ui()
        object OnSetNoteWallpaperClicked : Ui()

    }

    sealed class Inner : Msg() {
        data class CheckedEntryPoint(val value: Boolean): Inner()
        object ShowedKeyboardForExpandedFab : Inner()
        data class FetchedPassedNoteResult(val note: NoteUi) : Inner()
        data class UpdatedCurrentNoteTitle(val text: String) : Inner()
        data class UpdatedCurrentNoteMessage(val text: String) : Inner()
        data class FetchedPassedCurrentFolderId(val id: Long) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNote : Cmd()
    data class SaveNote(val note: NoteUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object ShowToastMaxLengthNoteExceed : Eff()
    object ShowNoteUpdateSnackBar : Eff()
    object ShowKeyboardForExpandedFab : Eff()
}