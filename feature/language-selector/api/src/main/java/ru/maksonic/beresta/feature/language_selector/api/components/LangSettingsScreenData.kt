package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 19.02.2023
 */
@Serializable
data class LangSettingsScreenData(
    @SerializedName("top_bar_title") val topBarTitle: String = "",
    @SerializedName("title_general") val titleGeneral: String = "",
    @SerializedName("title_account") val titleAccount: String = "",
    @SerializedName("title_support") val titleSupport: String = "",
    @SerializedName("item_switch_language") val itemSwitchLanguage: String = "",
    @SerializedName("item_switch_theme") val itemSwitchTheme: String = "",
    @SerializedName("item_change_account") val itemChangeAccount: String = "",
    @SerializedName("item_write_message") val itemWriteMessage: String = "",
    @SerializedName("item_privacy") val itemPrivacy: String = "",
    @SerializedName("item_terms_of_use") val itemTermsOfUse: String = "",
    @SerializedName("item_about_app") val itemAboutApp: String = "",
    @SerializedName("title_theme_system") val titleThemeSystem: String = "",
    @SerializedName("title_theme_light") val titleThemeLight: String = "",
    @SerializedName("title_theme_night") val themeTitleNight: String = "",
    @SerializedName("title_theme_high_contrast") val themeTitleHighContrast: String = ""

)
