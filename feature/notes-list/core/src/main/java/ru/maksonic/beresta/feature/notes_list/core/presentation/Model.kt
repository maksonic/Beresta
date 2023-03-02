package ru.maksonic.beresta.feature.notes_list.core.presentation

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 21.02.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel.InitialWithLoading,
    val notes: NoteUi.Collection = NoteUi.Collection.Empty,
    val filters: FilterChipUi.Collection = FilterChipUi.Collection.Preview,
    val errorMsg: String = "Неизвестная ошибка",
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnChipFilterClicked(val id: Int): Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesCollection(val notes: NoteUi.Collection) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchData : Cmd()
}

sealed class Eff : ElmEffect {
}