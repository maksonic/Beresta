package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.program

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.PinCodeInfo
import ru.maksonic.beresta.feature.notes.api.domain.usecase.DeleteHiddenNotesUseCase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @Author maksonic on 03.08.2023
 */
val DEFAULT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.of(1970, 1, 1, 1, 0))


class HiddenNotesPasswordProgram(
    private val deleteHiddenNotesUseCase: DeleteHiddenNotesUseCase,
    private val passwordStore: HiddenNotesApi.Feature,
    private val pinSecurePrefs: HiddenNotesApi.Feature.SecurePrefs,
    private val pinFailCounter: HiddenNotesApi.Feature.PinFailCounter,
    private val ioDispatcher: CoroutineDispatcher,
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
            is Cmd.RunPinCoolDownWork -> runPinCoolDownWork(cmd.count)
            is Cmd.ResetPinFailCounter -> resetPinFailCounter()
            is Cmd.ResetPinFailCoolDown -> resetPinFailCoolDown()
            else -> {}
        }
    }

    private suspend fun fetchPinCodeStatus(consumer: (Msg) -> Unit) {
        combine(passwordStore.pinCode, pinFailCounter.state) { pinCode, failInfo ->
            val currentDate = LocalDateTime.now()
            val timestampDate = failInfo.timestamp

            if (timestampDate != null) {
                val delay = currentDate.second - timestampDate.second
                if (delay in 0..15) {
                    consumer(
                        Msg.Inner.FetchedPinCodeStatus(
                            PinCodeInfo(
                                isCreated = pinCode.isNotEmpty(),
                                isCoolDown = true,
                                coolDownDelay = delay,
                                failCount = failInfo.failCount
                            )
                        )
                    )
                } else {
                    consumer(
                        Msg.Inner.FetchedPinCodeStatus(
                            PinCodeInfo(
                                isCreated = pinCode.isNotEmpty(),
                                isCoolDown = false,
                                coolDownDelay = failInfo.tick,
                                failCount = failInfo.failCount
                            )
                        )
                    )
                }
            } else {
                consumer(
                    Msg.Inner.FetchedPinCodeStatus(
                        PinCodeInfo(
                            isCreated = pinCode.isNotEmpty(),
                            isCoolDown = false,
                            coolDownDelay = failInfo.tick,
                            failCount = failInfo.failCount
                        )
                    )
                )
            }

        }.collect()
    }
    /* private suspend fun fetchPinCodeStatus(consumer: (Msg) -> Unit) {
        combine(passwordStore.pinCode, pinFailCounter.state) { pinCode, failInfo ->
            consumer(
                Msg.Inner.FetchedPinCodeStatus(
                    PinCodeInfo(
                        isCreated = pinCode.isNotEmpty(),
                        isCoolDown = failInfo.failCount >= 1,
                        coolDownDelay = failInfo.currentDelay.toString(),
                        failCount = failInfo.failCount
                    )
                )
            )
        }.collect()
    }*/

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
                pinFailCounter.updateCounter(0).run { consumer(Msg.Inner.SuccessCodeResult) }
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_VERIFIED))
            }
            this.coroutineContext.job.cancel()
        }
    }

    private suspend fun resetPinCode() {
        passwordStore.updatePassword("")
        deleteHiddenNotesUseCase.invoke()
        resetPinFailCounter()
    }

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinSecurePrefs.updatePinVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinSecurePrefs.updateKeyTapVisibility(isVisible)

    private suspend fun runPinCoolDownWork(count: Int) {
        pinFailCounter.updateTimestamp(LocalDateTime.now())
      //  pinFailCounter.updateTick(15)
        pinFailCounter.updateCounter(count)
        pinFailCounter.updateCoolDown(true)
      //  alarmScheduler.schedule()
    }

    private suspend fun resetPinFailCounter() = pinFailCounter.reset()

    private suspend fun resetPinFailCoolDown() {
        pinFailCounter.updateCoolDown(false)
        pinFailCounter.updateTimestamp(null)
    }
}

