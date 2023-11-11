package ru.maksonic.beresta.feature.tags_list.data.tags

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @Author maksonic on 05.11.2023
 */
@Serializable
data class TagItemModel(@SerialName("id") val id: Long, @SerialName("title") val title: String)