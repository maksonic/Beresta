package ru.maksonic.beresta.feature.notes_list.domain

import java.util.*

/**
 * @Author maksonic on 19.12.2022
 */
data class NoteDomain(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: Calendar = Calendar.getInstance(),
)