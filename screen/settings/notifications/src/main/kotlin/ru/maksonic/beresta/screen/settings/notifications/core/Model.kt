package ru.maksonic.beresta.screen.settings.notifications.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 07.07.2023
 */
@Stable
@Immutable
data class Model(
    val isEnabledVibration: Boolean
) : ElmModel {
    companion object {
        val Initial = Model(isEnabledVibration = true)
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        data object OnVibrationItemClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedVibrationStateResult(val isEnabled: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchVibrationState: Cmd()
    data class UpdateVibrationState(val isEnabled: Boolean) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
}