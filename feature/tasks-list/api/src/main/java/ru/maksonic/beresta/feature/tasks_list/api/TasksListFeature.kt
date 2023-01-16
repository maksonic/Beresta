package ru.maksonic.beresta.feature.tasks_list.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

/**
 * @Author maksonic on 22.12.2022
 */
interface TasksListFeature {
    @Composable
    fun Screen()

    val state: StateFlow<TasksSharedState>

}