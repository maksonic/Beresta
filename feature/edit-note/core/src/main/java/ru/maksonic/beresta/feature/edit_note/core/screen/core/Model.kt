package ru.maksonic.beresta.feature.edit_note.core.screen.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*

/**
 * @Author maksonic on 04.03.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val inputTitle: String = "",
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateInputTitle(val text: String): Inner()
    }
}

sealed class Cmd : ElmCommand {
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
}