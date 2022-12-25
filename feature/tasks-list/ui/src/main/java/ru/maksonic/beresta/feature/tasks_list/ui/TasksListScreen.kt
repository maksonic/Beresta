package ru.maksonic.beresta.feature.tasks_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature

/**
 * @Author maksonic on 24.12.2022
 */
class TasksListScreen : TasksListFeature {
    @Composable
    override fun Screen() {
        Content()
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        Column(
            modifier
                .fillMaxSize()
                .background(Color.Magenta)) {

        }
    }
}