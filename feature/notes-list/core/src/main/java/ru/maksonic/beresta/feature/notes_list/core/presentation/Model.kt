package ru.maksonic.beresta.feature.notes_list.core.presentation

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection

/**
 * @Author maksonic on 21.02.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val notes: NotesCollection = NotesCollection(emptyList()),
    val errorMsg: String = "Неизвестная ошибка",
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
    }

    sealed class Inner : Msg() {
        data class FetchedNotesCollection(val notes: NotesCollection) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchData : Cmd()
}

sealed class Eff : ElmEffect {
}