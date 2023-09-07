package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program

import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg

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