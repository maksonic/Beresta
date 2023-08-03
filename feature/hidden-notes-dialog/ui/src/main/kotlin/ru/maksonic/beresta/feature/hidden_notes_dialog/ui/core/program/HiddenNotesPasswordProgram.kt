package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.notes.api.domain.usecase.DeleteHiddenNotesUseCase

/**
 * @Author maksonic on 03.08.2023
 */
class HiddenNotesPasswordProgram(
    private val deleteHiddenNotesUseCase: DeleteHiddenNotesUseCase,
    private val passwordStore: HiddenNotesApi.Feature,
    private val pinSecurePrefs: HiddenNotesApi.Feature.SecurePrefs
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val PIN_RESULT_DELAY = 100L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchSavedPinCodeStatus -> fetchPinCodeStatus(consumer)
            is Cmd.FetchPinSecurePrefs -> fetchPinSecurePrefs(consumer)
            is Cmd.CreatePinCode -> createPinCode(cmd.code, cmd.cachedCode, consumer)
            is Cmd.VerifyPinCode -> verifyPinCode(cmd.scope, cmd.code, consumer)
            is Cmd.ResetHiddenNotesPinCode -> resetPinCode()
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
            else -> {}
        }
    }

    private suspend fun fetchPinCodeStatus(consumer: (Msg) -> Unit) {
        passwordStore.pinCode.collect {
            consumer(Msg.Inner.FetchedPinCodeStatus(it.isNotEmpty()))
        }
    }

    private suspend fun fetchPinSecurePrefs(consumer: (Msg) -> Unit) {
        pinSecurePrefs.state.collect { state ->
            consumer(Msg.Inner.FetchedPinSecurePrefs(state))
        }
    }

    private suspend fun createPinCode(code: String, cachedCode: String, consumer: (Msg) -> Unit) {
        delay(PIN_RESULT_DELAY)
        if (cachedCode.isEmpty()) {
            consumer(Msg.Inner.UpdatedCacheCode(code))
        } else {
            if (cachedCode == code) {
                passwordStore.updatePassword(code)
                consumer(Msg.Inner.SuccessCodeResult)
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_MATCHED))
            }
        }
    }

    private suspend fun verifyPinCode(
        scope: CoroutineScope,
        code: String,
        consumer: (Msg) -> Unit
    ) = scope.launch {
        delay(PIN_RESULT_DELAY)
        passwordStore.pinCode.cancellable().collect { savedCode ->
            if (savedCode == code) {
                consumer(Msg.Inner.SuccessCodeResult)
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_VERIFIED))
            }
            this.coroutineContext.job.cancel()
        }
    }

    private suspend fun resetPinCode() =
        passwordStore.updatePassword("").run { deleteHiddenNotesUseCase.invoke() }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinSecurePrefs.updatePinVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinSecurePrefs.updateKeyTapVisibility(isVisible)
}