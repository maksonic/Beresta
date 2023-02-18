package ru.maksonic.beresta.screen.settings.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.ui.theme.AppTheme

/**
 * @Author maksonic on 23.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel()
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        data class SwitchAppTheme(val theme: AppTheme): Ui()
    }

    sealed class Inner : Msg() {
    }
}

sealed class Cmd : ElmCommand {
    data class SetTheme(val theme: AppTheme): Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
}