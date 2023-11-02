package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program

import ru.maksonic.beresta.common.core.ScreenCaptureManager
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesScreenCaptureProgram(
    private val screenCaptureManager: ScreenCaptureManager
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateScreenCapturePermission -> screenCaptureManager.update(cmd.isEnabled)
            else -> {}
        }
    }
}