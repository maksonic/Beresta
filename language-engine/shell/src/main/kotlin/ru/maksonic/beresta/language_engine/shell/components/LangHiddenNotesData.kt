package ru.maksonic.beresta.language_engine.shell.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 20.07.2023
 */
@Serializable
data class LangHiddenNotesData(
    @SerializedName("top_bar_title") val topBarTitle: String = "",
    @SerializedName("dialog_title") val dialogTitle: String = "",
    @SerializedName("dialog_message") val dialogMessage: String = "",
    @SerializedName("hint_create_code") val hintCreateCode: String = "",
    @SerializedName("hint_create_repeat_code") val hintRepeatCode: String = "",
    @SerializedName("hint_verify_code") val hintVerifyCode: String = "",
    @SerializedName("hint_fail_creation") val hintFailCreationCode: String = "",
    @SerializedName("hint_fail_verification") val hintFailVerificationCode: String = "",
    @SerializedName("hint_forget_pin_code") val hintForgetPinCode: String = "",
    @SerializedName("dialog_title_reset_pin") val dialogTitleResetPin: String = "",
    @SerializedName("dialog_message_reset_pin") val dialogMessageResetPin: String = ""
)
