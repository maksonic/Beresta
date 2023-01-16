package ru.maksonic.beresta.feature.tasks_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.*
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksSharedState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.functional.isScrolledBottom

/**
 * @Author maksonic on 24.12.2022
 */
class TasksListScreen : TasksListFeature {
    private val mutableSharedTasksState = MutableStateFlow(TasksSharedState())
    override val state: StateFlow<TasksSharedState>
        get() = mutableSharedTasksState.asStateFlow()

    data class Task(
        val id: Long,
        val title: String,
    )

    private val fakeData: List<Task> = buildList {
        repeat(100) { add(Task(id = it.toLong(), "Some Task")) }
    }

    @Composable
    override fun Screen() {
        Content(
            showMainToolbar = { isShow ->
                mutableSharedTasksState.update { state ->
                    state.copy(isShowMainToolbar = isShow)
                }
            },
            showBottomPanel = { isShow ->
                mutableSharedTasksState.update { state ->
                    state.copy(isShowBottomPanel = isShow)
                }
            },
            isColoredTopBar = { isColored ->
                mutableSharedTasksState.update { state ->
                    state.copy(isColoredTopBar = isColored)
                }
            }
        )
    }

    @Composable
    private fun Content(
        showMainToolbar: (Boolean) -> Unit,
        showBottomPanel: (Boolean) -> Unit,
        isColoredTopBar: (Boolean) -> Unit,
        modifier: Modifier = Modifier
    ) {
        val tasksScrollState = rememberLazyListState()
        val tasksList = remember { mutableStateOf(fakeData) }
        val isScrollUp = tasksScrollState.isScrollUp()
        val isScrolledEnd = tasksScrollState.isScrolledBottom()

        LaunchedEffect(tasksScrollState) {
            snapshotFlow { tasksScrollState.firstVisibleItemIndex }
                .map { index -> index == 0 }
                .distinctUntilChanged()
                .collect {
                    isColoredTopBar(it)
                }
        }

        LaunchedEffect(isScrollUp) {
            showMainToolbar(isScrollUp)
            showBottomPanel(isScrollUp)
        }
        if (isScrolledEnd) {
            LaunchedEffect(Unit) {
                showBottomPanel(true)
            }
        }

        LazyColumn(
            modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            state = tasksScrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(tasksList.value, key = { task ->
                task.id
            }
            ) { task ->
                Text(
                    text = task.title,
                    modifier = modifier
                        .padding(top = dp32)
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }

            item() {
                val bottomPadding = Theme.widgetSize.bottomPanelHeightIdle
                Spacer(
                    modifier
                        .fillMaxWidth()
                        .height(bottomPadding)
                )
            }
        }
    }
}