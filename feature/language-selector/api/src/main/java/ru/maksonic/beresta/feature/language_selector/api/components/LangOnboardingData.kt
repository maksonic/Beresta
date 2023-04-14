package ru.maksonic.beresta.feature.language_selector.api.components

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 17.02.2023
 */
@Serializable
data class OnboardingDataItem(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("image") val image: String = ""
)

@Serializable
data class LangOnboardingData(
    @SerializedName("btn_title_next") val btnTitleNext: String = "",
    @SerializedName("btn_title_sync") val btnTitleSync: String = "",
    @SerializedName("btn_title_skip_sync") val btnTitleSkipSync: String = "",
    @SerializedName("onboarding_data") val data: Array<OnboardingDataItem>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LangOnboardingData

        if (btnTitleNext != other.btnTitleNext) return false
        if (btnTitleSync != other.btnTitleSync) return false
        if (btnTitleSkipSync != other.btnTitleSkipSync) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = btnTitleNext.hashCode()
        result = 31 * result + btnTitleSync.hashCode()
        result = 31 * result + btnTitleSkipSync.hashCode()
        result = 31 * result + data.contentHashCode()
        return result
    }
}