package ru.maksonic.beresta.language_engine.shell.components.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 15.09.2023
 */
@Serializable
data class LangComponentSettingsNotifications(
    @SerialName("top_bar_title") val topBarTitle: String,
    @SerialName("title_sounds") val titleSounds: String,
    @SerialName("item_vibration") val itemVibration: String,
) {
    companion object {
        val Default = LangComponentSettingsNotifications(
            topBarTitle = "Notifications and sounds",
            titleSounds = "Sounds",
            itemVibration = "Vibration"
        )
    }
}