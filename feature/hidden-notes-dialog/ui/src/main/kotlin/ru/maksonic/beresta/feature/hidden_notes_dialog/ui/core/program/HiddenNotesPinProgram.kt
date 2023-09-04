package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import ru.maksonic.beresta.core.system.defaultEpochSecond
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.notes.api.domain.usecase.DeleteHiddenNotesUseCase
import java.time.LocalDateTime

/**
 * @Author maksonic on 29.08.2023
 */
class HiddenNotesPinProgram(
    private val deleteHiddenNotesUseCase: DeleteHiddenNotesUseCase,
    private val passwordStore: HiddenNotesApi.Feature,
    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter,
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val PIN_RESULT_DELAY = 100L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinStatus -> fetchPinStatus(consumer)
            is Cmd.CreatePin -> createPin(cmd.code, cmd.cachedCode, cmd.scope, consumer)
            is Cmd.VerifyPin -> verifyPin(cmd.scope, cmd.code, consumer)
            is Cmd.ResetPin -> resetPin()
            is Cmd.ResetPinFailCounter -> pinFailCounter.updateFailCounter(0)
            is Cmd.ShowPinCoolDownBlock -> showCoolDownBlock(cmd.failCount, cmd.endDate)
            is Cmd.HidePinCoolDownBlock -> hideCoolDownBlock()
            else -> {}
        }
    }

    private suspend fun fetchPinStatus(consumer: (Msg) -> Unit) =
        combine(passwordStore.pinCode, pinFailCounter.state) { pinCode, failInfo ->
            consumer(
                Msg.Inner.FetchedPinStatus(
                    PinInfo(
                        isCreated = pinCode.isNotEmpty(),
                        isCoolDown = failInfo.isCoolDown,
                        endDate = failInfo.endDate,
                        failCount = failInfo.failCount
                    )
                )
            )
        }.collect()

    private suspend fun createPin(
        code: String,
        cachedCode: String,
        scope: CoroutineScope,
        consumer: (Msg) -> Unit
    ) = scope.launch {
        delay(PIN_RESULT_DELAY)
        if (cachedCode.isEmpty()) {
            consumer(Msg.Inner.UpdatedCachePin(code))
        } else {
            if (cachedCode == code) {
                passwordStore.updatePassword(code)
                consumer(Msg.Inner.SuccessCodeResult)
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_MATCHED))
            }
        }
        this.coroutineContext.cancel()
    }

    private suspend fun verifyPin(scope: CoroutineScope, code: String, consumer: (Msg) -> Unit) =
        scope.launch {
            delay(PIN_RESULT_DELAY)
            passwordStore.pinCode.cancellable().collect { savedCode ->
                if (savedCode == code) {
                    pinFailCounter.updateFailCounter(0)
                    consumer(Msg.Inner.SuccessCodeResult)
                } else {
                    consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_VERIFIED))
                }
                this.coroutineContext.job.cancel()
            }
        }

    private suspend fun resetPin() {
        passwordStore.updatePassword("")
        deleteHiddenNotesUseCase.invoke()
        pinFailCounter.updateFailCounter(0)
    }

    private suspend fun showCoolDownBlock(failCount: Int, endDate: Long) {
        pinFailCounter.updateCoolDown(true, endDate)
        pinFailCounter.updateFailCounter(failCount)
    }

    private suspend fun hideCoolDownBlock() {
        pinFailCounter.updateCoolDown(
            isCoolDown = false,
            endTime = LocalDateTime.now().defaultEpochSecond
        )
    }
}