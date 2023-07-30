package ru.maksonic.beresta.feature.edit_note.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.core.ElmBaseModel
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi

/**
 * @Author maksonic on 26.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val isEntryPoint: Boolean,
    val currentNote: NoteUi,
) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            isEntryPoint = false,
            currentNote = NoteUi.Default,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnSaveNoteClicked(val currentFolderId: Long, val isHiddenNote: Boolean) : Ui()
        object OnTopBarBackPressed : Ui()

        //Idle BottomBar actions
        object OnStartRecordVoiceClicked : Ui()
        object OnAddImagesClicked : Ui()
        object OnAddCameraSnapshotClicked : Ui()
        object OnSetNoteWallpaperClicked : Ui()

    }

    sealed class Inner : Msg() {
        data class CheckedEntryPoint(val value: Boolean) : Inner()
        object ShowedKeyboardForExpandedFab : Inner()
        data class FetchedPassedNoteResult(val note: NoteUi) : Inner()
        data class UpdatedCurrentNoteTitle(val text: String) : Inner()
        data class UpdatedCurrentNoteMessage(val text: String) : Inner()
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
    object CollapseFab : Eff()
}