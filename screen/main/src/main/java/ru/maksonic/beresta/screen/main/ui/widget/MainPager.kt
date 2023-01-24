package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesSharedState
import ru.maksonic.beresta.feature.tasks_list.api.TasksSharedState
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.screen.main.ui.core.Screen
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 20.12.2022
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainPager(
    msg: SendMessage,
    pagerState: PagerState,
    userScrollEnabled: Boolean,
    notes: Pair<@Composable () -> Unit, NotesSharedState>,
    tasks: Pair<@Composable () -> Unit, TasksSharedState>,
    modifier: Modifier = Modifier
) {
    OverscrollBehavior {
        HorizontalPager(
            count = 2,
            state = pagerState,
            userScrollEnabled = userScrollEnabled,
            modifier = modifier.fillMaxSize()
        ) { page ->
            Page(
                msg = msg,
                page = { page },
                pagerState = pagerState,
                notes = notes,
                tasks = tasks
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun Page(
    msg: SendMessage,
    page: () -> Int,
    pagerState: PagerState,
    notes: Pair<@Composable () -> Unit, NotesSharedState>,
    tasks: Pair<@Composable () -> Unit, TasksSharedState>,
) {

    if (pagerState.currentPage == PageItem.NOTES.pageValue) {
        LaunchedEffect(notes.second.isShowMainToolbar) {
            msg(Screen.Msg.Inner.SetTopBarVisibility(notes.second.isShowMainToolbar))
        }

        LaunchedEffect(notes.second.isColoredTopBar) {
            msg(Screen.Msg.Inner.SetColoredTopBar(!notes.second.isColoredTopBar))
        }

        LaunchedEffect(notes.second.isShowBottomPanel) {
            msg(Screen.Msg.Inner.SetBottomVisibility(notes.second.isShowBottomPanel))
        }
    }
    if (pagerState.currentPage == PageItem.TASKS.pageValue) {
        LaunchedEffect(tasks.second.isShowMainToolbar) {
            msg(Screen.Msg.Inner.SetTopBarVisibility(tasks.second.isShowMainToolbar))
        }

        LaunchedEffect(tasks.second.isColoredTopBar) {
            msg(Screen.Msg.Inner.SetColoredTopBar(!tasks.second.isColoredTopBar))
        }

        LaunchedEffect(tasks.second.isShowBottomPanel) {
            msg(Screen.Msg.Inner.SetBottomVisibility(tasks.second.isShowBottomPanel))
        }
    }

    //Apply pages ui
    if (page() == PageItem.NOTES.pageValue) notes.first() else tasks.first()
}