package ru.maksonic.beresta.screen.settings.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*

/**
 * @Author maksonic on 23.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel()
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object TopBarBackPressed : Ui()
    }

    sealed class Inner : Msg() {
    }
}

sealed class Cmd : ElmCommand {
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
}