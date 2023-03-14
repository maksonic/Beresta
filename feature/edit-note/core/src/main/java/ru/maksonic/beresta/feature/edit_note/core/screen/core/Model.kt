package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel.EditorPanelState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.BottomSheetEditorState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.SheetContent
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 04.03.2023
 */

data class BottomSheetEditor(
    val currentContent: SheetContent = SheetContent.NOTHING,
    val state: BottomSheetEditorState = BottomSheetEditorState.COLLAPSED
)
@Stable
data class Model constructor(
    val base: BaseModel = BaseModel(isLoading = true),
    val currentNote: NoteUi = NoteUi(),
    val isNewNote: Boolean = false,
    val isVisibleEditorPanel: Boolean = true,
    val titleField: TextFieldValue = TextFieldValue(""),
    val messageField: TextFieldValue = TextFieldValue(""),
    val editorPanelState: EditorPanelState = EditorPanelState.IDLE,
    val editorSheet: BottomSheetEditor = BottomSheetEditor()
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnChangeNoteWallpaperClicked : Ui()
        object OnSetIdleEditorPanelStateClicked : Ui()
        object OnHideModalSheetClicked : Ui()
    }

    sealed class Inner : Msg() {
        object ShowKeyboardWithFocusOnTitle : Inner()
        data class UpdatedInputTitle(val textField: TextFieldValue) : Inner()
        data class UpdatedInputMessage(val msgField: TextFieldValue) : Inner()
        data class UpdatedFabIcon(val fabIconState: MutableState<Boolean>) : Inner()
        data class UpdatedEditorPanelVisibility(val isVisible: Boolean) : Inner()
        data class UpdatedNoteWallpaper(val id: Int): Inner()
        data class FetchedFabStateValue(val isExpanded: Boolean) : Inner()
        object ShowedMaxLengthNoteInputWarning : Inner()
        object ResetBottomSheetContent : Inner()
    }
}

sealed class Cmd : ElmCommand {}

sealed class Eff : ElmEffect {
    object SetInitialExpandedState : Eff()
    object ShowToastMaxLengthNoteExceed : Eff()
    object NavigateBack : Eff()
    object HideSystemKeyboard : Eff()
    object CollapseFab : Eff()
}