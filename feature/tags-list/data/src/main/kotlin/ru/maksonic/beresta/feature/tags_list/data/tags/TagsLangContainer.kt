package ru.maksonic.beresta.feature.tags_list.data.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 05.11.2023
 */
@Serializable
data class TagsLangContainer(
    @SerialName("russian") val russian: List<TagItemModel>,
    @SerialName("english") val english: List<TagItemModel>,
    @SerialName("chinese") val chinese: List<TagItemModel>,
    @SerialName("chinese_tr") val chineseTr: List<TagItemModel>,
)