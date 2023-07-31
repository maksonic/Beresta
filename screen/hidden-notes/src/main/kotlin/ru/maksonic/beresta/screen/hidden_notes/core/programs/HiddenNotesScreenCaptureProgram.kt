package ru.maksonic.beresta.screen.hidden_notes.core.programs

import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg

/**
 * @Author maksonic on 31.07.2023
 */
class HiddenNotesScreenCaptureProgram(
    private val screenCaptureManager: ScreenCaptureManager,
    ) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.AllowScreenCapture -> screenCaptureManager.allowScreenCapture()
            else -> {}
        }
    }
}