package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.ModalSheetContent
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.editor.EditorPanelState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 04.03.2023
 */
@Stable
data class Model @OptIn(ExperimentalMaterialApi::class) constructor(
    val base: BaseModel = BaseModel(isLoading = true),
    val createdNote: NoteUi = NoteUi(),
    val isExpandedFabState: Boolean = false,
    val isVisibleEditorPanel: Boolean = true,
    val titleField: TextFieldValue = TextFieldValue(""),
    val messageField: TextFieldValue = TextFieldValue(""),
    val editorPanelState: EditorPanelState = EditorPanelState.IDLE,
    val currentSheetContent: ModalSheetContent = ModalSheetContent.NOTHING,
    val modalBottomSheetState: ModalBottomSheetState = ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, isSkipHalfExpanded = true
    )
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnChangeNoteWallpaperClicked : Ui()
        object OnSetIdleEditorPanelStateClicked : Ui()
        object OnHideModalSheetClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedInputTitle(val textField: TextFieldValue) : Inner()
        data class UpdatedInputMessage(val msgField: TextFieldValue) : Inner()
        data class FetchedFabStateValue(val isExpanded: Boolean) : Inner()
        data class UpdatedEditorPanelVisibility(val isVisible: Boolean) : Inner()
        object ShowedMaxLengthNoteInputWarning : Inner()
        object ShowKeyboard : Inner()
        object HideKeyboard : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object ListenFabState : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowToastMaxLengthNoteExceed : Eff()
    object NavigateBack : Eff()
    object ShowSystemKeyboard : Eff()
    object HideSystemKeyboard : Eff()
    object CollapseFab : Eff()
    object ShowModalSheet : Eff()
    object HideModalSheet : Eff()
}