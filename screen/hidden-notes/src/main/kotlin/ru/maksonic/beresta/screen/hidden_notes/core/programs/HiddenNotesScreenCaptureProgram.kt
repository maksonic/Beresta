package ru.maksonic.beresta.screen.hidden_notes.core.programs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.screen.hidden_notes.core.Cmd
import ru.maksonic.beresta.screen.hidden_notes.core.Msg

/**
 * @Author maksonic on 31.07.2023
 */
class HiddenNotesScreenCaptureProgram(
    private val screenCaptureManager: ScreenCaptureManager,
    private val ioDispatcher: CoroutineDispatcher,
    ) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.UpdateScreenCapturePermission -> {
                withContext(ioDispatcher) {
                    screenCaptureManager.update(cmd.isEnabled)
                }
            }
            else -> {}
        }
    }
}