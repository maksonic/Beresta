package ru.maksonic.beresta.feature.edit_note.ui.core.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.NoteUi

/**
 * @Author maksonic on 26.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(),
    val note: NoteUi = NoteUi()
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnSaveNoteClicked(val note: NoteUi) : Ui()
        object OnTopBarBackClicked : Ui()

    }

    sealed class Inner : Msg() {
        data class FetchingNoteResult(val note: NoteUi): Inner()
        data class UpdateCurrentTitle(val text: String): Inner()
        data class UpdateCurrentMessage(val text: String): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNote : Cmd()
    data class SaveNote(val note: NoteUi) : Cmd()
    data class UpdateCurrentNote(val note: NoteUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object Nothing : Eff()
}