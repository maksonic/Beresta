package ru.maksonic.beresta.language_engine.shell.components.settings

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 15.09.2023
 */
@Serializable
data class LangSettingsNotificationsData(
    @SerializedName("top_bar_title") val topBarTitle: String = "",
    @SerializedName("title_sounds") val titleSounds: String = "",
@SerializedName("item_vibration") val itemVibration: String = "",
)