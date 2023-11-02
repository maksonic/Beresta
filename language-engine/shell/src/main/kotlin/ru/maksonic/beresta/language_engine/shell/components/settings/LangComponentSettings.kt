package ru.maksonic.beresta.language_engine.shell.components.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 28.09.2023
 */
@Serializable
data class LangComponentSettings(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("title_general") val titleGeneral: String,
    @SerialName("title_account") val titleAccount: String,
    @SerialName("title_support") val titleSupport: String,
    @SerialName("item_switch_language") val itemSwitchLanguage: String,
    @SerialName("item_switch_theme") val itemSwitchTheme: String,
    @SerialName("item_change_account") val itemChangeAccount: String,
    @SerialName("item_write_message") val itemWriteMessage: String,
    @SerialName("item_privacy") val itemPrivacy: String,
    @SerialName("item_terms_of_use") val itemTermsOfUse: String,
    @SerialName("item_about_app") val itemAboutApp: String,
    @SerialName("title_theme_system") val titleThemeSystem: String,
    @SerialName("title_theme_light") val titleThemeLight: String,
    @SerialName("title_theme_night") val themeTitleNight: String,
    @SerialName("title_theme_dark") val themeTitleDark: String,
    @SerialName("dialog_language_picker_title") val dialogLanguagePickerTitle: String,
    @SerialName("dialog_theme_picker_title") val dialogThemePickerTitle: String,
) {
    companion object {
        val Default = LangComponentSettings(
            topBarTitle = "Settings",
            titleGeneral = "Main",
            titleAccount = "Account",
            titleSupport = "Support",
            itemSwitchLanguage = "Language",
            itemSwitchTheme = "Theme",
            itemChangeAccount = "Edit Profile",
            itemWriteMessage = "Write to developer",
            itemPrivacy = "Privacy Policy",
            itemTermsOfUse = "User Agreement",
            itemAboutApp = "About app",
            titleThemeSystem = "System",
            titleThemeLight = "Light",
            themeTitleNight = "Night",
            themeTitleDark = "Dark",
            dialogLanguagePickerTitle = "Select an app language",
            dialogThemePickerTitle = "Select an app theme",
        )
    }
}
