package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesSharedState
import ru.maksonic.beresta.feature.tasks_list.api.TasksSharedState
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.screen.main.ui.core.Msg
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 20.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainPager(
    send: SendMessage,
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
                send = send,
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
    send: SendMessage,
    page: () -> Int,
    pagerState: PagerState,
    notes: Pair<@Composable () -> Unit, NotesSharedState>,
    tasks: Pair<@Composable () -> Unit, TasksSharedState>,
) {

    if (pagerState.currentPage == PageItem.NOTES.pageValue) {
        SideEffect {
            with(notes.second) {
                send(Msg.Inner.SetTopBarVisibility(isShowMainToolbar))
                send(Msg.Inner.SetColoredTopBar(!isColoredTopBar))
                send(Msg.Inner.SetBottomVisibility(isShowBottomPanel))
            }
        }
    } else {
        SideEffect {
           with(tasks.second) {
               send(Msg.Inner.SetTopBarVisibility(isShowMainToolbar))
               send(Msg.Inner.SetColoredTopBar(!isColoredTopBar))
               send(Msg.Inner.SetBottomVisibility(isShowBottomPanel))
           }
        }
    }

    //Set page ui
    if (page() == PageItem.NOTES.pageValue) notes.first() else tasks.first()
}