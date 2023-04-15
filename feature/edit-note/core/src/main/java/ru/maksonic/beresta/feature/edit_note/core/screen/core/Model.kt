package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 04.03.2023
 */
@Stable
@Immutable
data class Model constructor(
    val base: BaseModel = BaseModel(isLoading = true),
    val currentNote: NoteUi = NoteUi(),
    val isNewNote: Boolean = false,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnSaveNoteClicked : Ui()
        object OnTopBarBackPressed : Ui()
        object OnChangeNoteWallpaperClicked : Ui()
        object OnHideModalSheetClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedPassedNoteResult(val note: NoteUi) : Inner()
        data class FetchedPassedFabStateResult(val isExpanded: Boolean) : Inner()
        data class UpdatedInputTitle(val text: String) : Inner()
        data class UpdatedInputMessage(val text: String) : Inner()
        data class UpdatedNoteWallpaper(val id: Int) : Inner()
        object ShowKeyboardWithFocusOnTitle : Inner()
        object ShowedMaxLengthNoteInputWarning : Inner()
        object ResetBottomSheetContent : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNote : Cmd()
    data class SaveNote(val note: NoteUi) : Cmd()
    data class UpdateCurrentNote(val note: NoteUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowKeyboardWithTitleFocus : Eff()
    object ShowToastMaxLengthNoteExceed : Eff()
    object NavigateBack : Eff()
    object HideSystemKeyboard : Eff()
    object CollapseFab : Eff()
    object ShowFabDraftIcon : Eff()
    object ResetFabDraftIcon : Eff()
    object ExpandBottomSheet : Eff()
    object CollapseBottomSheet : Eff()
    object SetNothingSheetContent : Eff()
    object SetWallpaperSheetContent : Eff()
    object ShowNoteUpdateSnackBar : Eff()
}