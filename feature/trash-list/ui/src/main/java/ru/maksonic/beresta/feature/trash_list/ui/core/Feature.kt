package ru.maksonic.beresta.feature.trash_list.ui.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.NoteUi

/**
 * @Author maksonic on 23.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(),
    val removedNotes: List<NoteUi> = emptyList(),
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object TopBarBackPressed : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchingResult(val notes: List<NoteUi>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchRemovedNotes : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
}