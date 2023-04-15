package ru.maksonic.beresta.feature.edit_note.core.fab.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel

/**
 * @Author maksonic on 23.02.2023
 */
@Stable
data class WidgetModel(val base: BaseModel = BaseModel.InitialWithLoading) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCreateNewNoteClicked : Ui()
        object OnCollapseFabClicked : Ui()
    }
}

sealed class Cmd : ElmCommand {}

sealed class Eff : ElmEffect {
    object SetExpandFabSharedState : Eff()
    object SetCollapseFabSharedState : Eff()
}