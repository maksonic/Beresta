package ru.maksonic.beresta.feature.tasks_list.api

/**
 * @Author maksonic on 22.12.2022
 */
data class TaskUi(
    val id: Long = 0L,
    val title: String = "",
    val message: String = "",
    val dateCreation: String = "",
)