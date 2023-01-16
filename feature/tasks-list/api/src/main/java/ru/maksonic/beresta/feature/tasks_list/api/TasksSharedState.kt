package ru.maksonic.beresta.feature.tasks_list.api

/**
 * @Author maksonic on 15.01.2023
 */
data class TasksSharedState(
    val isShowMainToolbar: Boolean = true,
    val isShowBottomPanel: Boolean = true,
    val isColoredTopBar: Boolean = false
)