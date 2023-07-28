package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import ru.maksonic.beresta.core.secure.ScreenCaptureManager
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.ui.PinFailStatus

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesEnterPasswordProgram(
    private val screenCaptureManager: ScreenCaptureManager,
    private val passwordStore: HiddenNotesApi.Feature
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchSavedPinCodeStatus -> fetchPinCodeStatus(consumer)
            is Cmd.UpdateScreenCapturePermission -> updateScreenCapturePermission(cmd.isEnabled)
            is Cmd.CreatePinCode -> createPinCode(cmd.code, cmd.cachedCode, consumer)
            is Cmd.VerifyPinCode -> verifyPinCode(cmd.code, consumer)
        }
    }

    private suspend fun fetchPinCodeStatus(consumer: (Msg) -> Unit) {
        passwordStore.pinCode.collect {
            consumer(Msg.Inner.FetchedPinCodeStatus(it.isNotEmpty()))
        }
    }

    private fun updateScreenCapturePermission(isEnabled: Boolean) =
        if (isEnabled) screenCaptureManager.enableScreenCapture()
        else screenCaptureManager.disableScreenCapture()

    private suspend fun createPinCode(code: String, cachedCode: String, consumer: (Msg) -> Unit) {
        if (cachedCode.isEmpty()) {
            consumer(Msg.Inner.UpdatedCacheCode(code))
        } else {
            if (cachedCode == code) {
                passwordStore.updatePassword(code)
                consumer(Msg.Inner.SuccessCreatedCodeResult)
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_MATCHED))
            }
        }
    }

    private suspend fun verifyPinCode(code: String, consumer: (Msg) -> Unit) {
        passwordStore.pinCode.collect { savedCode ->
            if (savedCode == code) {
                consumer(Msg.Inner.SuccessVerifiedCodeResult)
            } else {
                consumer(Msg.Inner.FailureCodeResult(PinFailStatus.NOT_VERIFIED))
            }
        }
    }
}