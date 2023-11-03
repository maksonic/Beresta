package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.program

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.core.security.BiometricEngine
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.HiddenNotesPinCreator
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailInfo
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinFailManager
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.PinPrivacySettings
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.ui.PinFailStatus
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Cmd
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.Msg
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core.core.biometric.BiometricState
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.DeleteHiddenNotesUseCase
import ru.maksonic.beresta.platform.core.system.defaultEpochSecond
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import java.time.LocalDateTime

/**
 * @Author maksonic on 29.08.2023
 */
class HiddenNotesPinProgram(
    private val hiddenNotesPinCreator: HiddenNotesPinCreator,
    private val deleteHiddenNotesUseCase: DeleteHiddenNotesUseCase,
    private val pinPrivacySettings: PinPrivacySettings,
    private val pinFailManager: PinFailManager,
    private val biometricEngine: BiometricEngine,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val PIN_RESULT_DELAY = 100L
    }

    private val _cachedPin = MutableStateFlow(byteArrayOf())
    private val cachedPin = _cachedPin.asStateFlow()

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchPinStatus -> fetchPinStatus(consumer)
            is Cmd.CreatePin -> {
                createPin(cmd.code.encodeToByteArray(), cmd.isCachedFirstInput, consumer)
            }

            is Cmd.VerifyPin -> verifyPin(cmd.code, consumer)
            is Cmd.ResetPin -> resetPin()
            is Cmd.ResetPinFailCounter -> pinFailManager.updateFailCounter(0)
            is Cmd.ShowPinCoolDownBlock -> showCoolDownBlock(cmd.failCount, cmd.endDate)
            is Cmd.HidePinCoolDownBlock -> hideCoolDownBlock()
            is Cmd.UpdatePinVisibility -> updatePinVisibility(cmd.isVisible)
            is Cmd.UpdateKeyTapVisibility -> updateKeyTapVisibility(cmd.isVisible)
            is Cmd.UpdateBiometricAuthState -> enableBiometricAuth(cmd.isEnabled)
            is Cmd.ResetCachePin -> _cachedPin.value = byteArrayOf()
            is Cmd.ShowBiometricDialogByPinStatus -> showBiometricDialogByPinStatus(consumer)
            else -> {}
        }
    }

    private suspend fun fetchPinStatus(consumer: (Msg) -> Unit) = combine(
        hiddenNotesPinCreator.state, pinFailManager.state, pinPrivacySettings.state
    ) { isValid, info, privacy ->
        val isVisibleBiometricButton = privacy.isEnabledBiometric.and(biometricEngine.isAvailable)

        consumer(
            Msg.Inner.FetchedPinStatus(
                info = PinFailInfo(
                    isValid = isValid,
                    isCoolDown = info.isCoolDown,
                    endDate = info.endDate,
                    failCount = info.failCount
                ),
                privacy = privacy,
                isVisibleBiometricKeyboardButton = isVisibleBiometricButton
            )
        )
    }.collect()

    private suspend fun createPin(
        code: ByteArray,
        isCachedFirstInput: Boolean,
        consumer: (Msg) -> Unit
    ) {
        delay(PIN_RESULT_DELAY)
        if (!isCachedFirstInput) {
            _cachedPin.value = code
            consumer(Msg.Inner.UpdatedCachePin)
        } else {
            if (cachedPin.value.contentEquals(code)) {
                hiddenNotesPinCreator.createPin(code)
                if (biometricEngine.isAvailable) {
                    consumer(Msg.Inner.ShowedBiometricDialog(BiometricState.SIGN_UP))
                } else {
                    consumer(Msg.Inner.SuccessPinResult)
                }
            } else {
                consumer(Msg.Inner.FailurePinResult(PinFailStatus.NOT_MATCHED))
            }
        }
    }

    private suspend fun verifyPin(code: String, consumer: (Msg) -> Unit) {
        delay(PIN_RESULT_DELAY)
        hiddenNotesPinCreator.verifyPin(code.encodeToByteArray())
            .onSuccess { isValid ->
                if (isValid) {
                    pinFailManager.updateFailCounter(0)
                    consumer(Msg.Inner.SuccessPinResult)
                } else {
                    consumer(Msg.Inner.FailurePinResult(PinFailStatus.NOT_VERIFIED))
                }
            }
            .onFailure { consumer(Msg.Inner.FailurePinResult(PinFailStatus.NOT_VERIFIED)) }
    }

    private suspend fun resetPin() {
        deleteHiddenNotesUseCase.invoke()
        pinFailManager.updateFailCounter(0)
        hiddenNotesPinCreator.deletePin()
    }

    private suspend fun showCoolDownBlock(failCount: Int, endDate: Long) {
        pinFailManager.updateCoolDown(true, endDate)
        pinFailManager.updateFailCounter(failCount)
    }

    private suspend fun hideCoolDownBlock() = pinFailManager.updateCoolDown(
        isCoolDown = false, endTime = LocalDateTime.now().defaultEpochSecond
    )

    private suspend fun updatePinVisibility(isVisible: Boolean) =
        pinPrivacySettings.updateVisibility(isVisible)

    private suspend fun updateKeyTapVisibility(isVisible: Boolean) =
        pinPrivacySettings.updateKeyTapVisibility(isVisible)

    private suspend fun enableBiometricAuth(isEnabled: Boolean) =
        pinPrivacySettings.updateBiometricEnablement(isEnabled)

    private suspend fun showBiometricDialogByPinStatus(consumer: (Msg) -> Unit) {
        val verify = CoroutineScope(ioDispatcher).launch {
            hiddenNotesPinCreator.state.cancellable().collect { isValid ->
                val biometricState = if (isValid) BiometricState.LOGIN else BiometricState.SIGN_UP
                consumer(Msg.Inner.ShowedBiometricDialog(biometricState))
            }
        }
        verify.cancelAndJoin()
    }
}