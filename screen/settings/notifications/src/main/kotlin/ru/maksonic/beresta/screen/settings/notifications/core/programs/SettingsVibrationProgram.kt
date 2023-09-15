package ru.maksonic.beresta.screen.settings.notifications.core.programs

import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.screen.settings.notifications.core.Cmd
import ru.maksonic.beresta.screen.settings.notifications.core.Msg

/**
 * @Author maksonic on 15.09.2023
 */
class SettingsVibrationProgram(
    private val vibrationPerformer: VibrationPerformer
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchVibrationState -> fetchVibrationState(consumer)
            is Cmd.UpdateVibrationState -> updateVibrationState(cmd.isEnabled)
            else -> {}
        }
    }

    private suspend fun fetchVibrationState(consumer: (Msg) -> Unit) = vibrationPerformer.isEnabled
        .collect { isEnabled -> consumer(Msg.Inner.FetchedVibrationStateResult(isEnabled)) }

    private suspend fun updateVibrationState(isEnabled: Boolean) =
        vibrationPerformer.updateVibrationState(!isEnabled)
}