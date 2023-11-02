package ru.maksonic.beresta.language_engine.shell.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 20.07.2023
 */
@Serializable
data class LangComponentHiddenNotes(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("dialog_title") val dialogTitle: String,
    @SerialName("dialog_message") val dialogMessage: String,
    @SerialName("hint_cool_down_verification") val hintCoolDownVerification: String,
    @SerialName("hint_create_code") val hintCreateCode: String,
    @SerialName("hint_create_repeat_code") val hintRepeatCode: String,
    @SerialName("hint_verify_code") val hintVerifyCode: String,
    @SerialName("hint_forget_pin_code") val hintForgetPinCode: String,
    @SerialName("dialog_title_reset_pin") val dialogTitleResetPin: String,
    @SerialName("dialog_message_reset_pin") val dialogMessageResetPin: String,
    @SerialName("title_sign_up_biometric_dialog") val titleSignUpBiometricDialog: String,
    @SerialName("title_auth_biometric_dialog") val titleAuthBiometricDialog: String,
    @SerialName("description_biometric_dialog") val descriptionBiometricDialog: String,
    @SerialName("btn_title_negative_biometric_dialog") val btnTitleNegativeBiometricDialog: String,
) {
    companion object {
        val Default = LangComponentHiddenNotes(
            topBarTitle = "Hidden notes",
            dialogTitle = "Set PIN",
            dialogMessage = "The PIN code is used to access the hidden notes folder.\n\nThe PIN cannot be restored.\n\nIf you have forgotten your PIN, you can set a new one, but all hidden notes will be permanently deleted.",
            hintCoolDownVerification = "Too many tries. Please re-enter the PIN code via:",
            hintCreateCode = "Enter 6 digit code",
            hintRepeatCode = "Repeat the code again",
            hintVerifyCode = "Enter your PIN",
            hintForgetPinCode = "Create a new PIN?",
            dialogTitleResetPin = "Are you sure you want to create a new PIN?",
            dialogMessageResetPin = "Attention! All current hidden notes will be permanently deleted.",
            titleSignUpBiometricDialog = "Use a scanner?",
            titleAuthBiometricDialog = "Show hidden notes",
            descriptionBiometricDialog = "For quick access to a folder with hidden notes.",
            btnTitleNegativeBiometricDialog = "Do not use",
        )
    }
}
