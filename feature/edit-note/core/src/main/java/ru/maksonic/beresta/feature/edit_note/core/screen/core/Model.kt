package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 04.03.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val createdNote: NoteUi = NoteUi(),
    val isExpandedFabState: Boolean = false,
    val isVisibleEditorPanel: Boolean = true,
    val titleField: TextFieldValue = TextFieldValue(""),
    val messageField: TextFieldValue = TextFieldValue(""),
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedInputTitle(val textField: TextFieldValue): Inner()
        data class UpdatedInputMessage(val msgField: TextFieldValue): Inner()
        data class FetchedFabStateValue(val isExpanded: Boolean): Inner()
        data class UpdatedEditorPanelVisibility(val isVisible: Boolean): Inner()
        object ShowedMaxLengthNoteInputWarning : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object ListenFabState : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowSnackMaxLengthNoteExceed : Eff()
    object NavigateBack : Eff()
}