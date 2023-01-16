package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesSharedState
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksSharedState
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior

/**
 * @Author maksonic on 20.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainPager(
    notesPage: NotesListFeature,
    tasksPage: TasksListFeature,
    pagerState: () -> PagerState,
    notesFeature: NotesSharedState,
    tasksFeature: TasksSharedState,
    isVisibleTopBar: () -> MutableState<Boolean>,
    isColoredTopBar: () -> MutableState<Boolean>,
    isVisibleBottomPanel: () -> MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    OverscrollBehavior {
        HorizontalPager(
            count = 2,
            state = pagerState(),
            modifier = modifier.fillMaxSize()
        ) { page ->
            Page(
                page = { page },
                notesPage = notesPage,
                tasksPage = tasksPage,
                pagerState = pagerState,
                notesFeature = notesFeature,
                tasksFeature = tasksFeature,
                isVisibleTopBar = isVisibleTopBar,
                isColoredTopBar = isColoredTopBar,
                isVisibleBottomPanel = isVisibleBottomPanel,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun Page(
    notesPage: NotesListFeature,
    tasksPage: TasksListFeature,
    page: () -> Int,
    notesFeature: NotesSharedState,
    tasksFeature: TasksSharedState,
    isVisibleTopBar: () -> MutableState<Boolean>,
    isColoredTopBar: () -> MutableState<Boolean>,
    isVisibleBottomPanel: () -> MutableState<Boolean>,
    pagerState: () -> PagerState,
) {

    if (pagerState().currentPage == PageItem.NOTES.pageValue) {
        LaunchedEffect(notesFeature.isShowMainToolbar) {
            isVisibleTopBar().value = notesFeature.isShowMainToolbar
        }

        LaunchedEffect(notesFeature.isColoredTopBar) {
            isColoredTopBar().value = !notesFeature.isColoredTopBar
        }

        LaunchedEffect(notesFeature.isShowBottomPanel) {
            isVisibleBottomPanel().value = notesFeature.isShowBottomPanel
        }
    }
    if (pagerState().currentPage == PageItem.TASKS.pageValue) {
        LaunchedEffect(tasksFeature.isShowMainToolbar) {
            isVisibleTopBar().value = tasksFeature.isShowMainToolbar
        }

        LaunchedEffect(tasksFeature.isColoredTopBar) {
            isColoredTopBar().value = !tasksFeature.isColoredTopBar
        }

        LaunchedEffect(tasksFeature.isShowBottomPanel) {
            isVisibleBottomPanel().value = tasksFeature.isShowBottomPanel
        }
    }

    if (page() == PageItem.NOTES.pageValue) {
        notesPage.Screen()
    } else {
        tasksPage.Screen()
    }

    // TODO: WORKED
/* if (page() == PageItem.NOTES.pageValue) {
        LaunchedEffect(!notesFeature.isShowMainToolbar) {
            isVisibleTopBar().value = notesFeature.isShowMainToolbar
        }
        LaunchedEffect(!notesFeature.isShowBottomPanel) {
            isVisibleBottomPanel().value = notesFeature.isShowBottomPanel
        }
        notesPage.Screen()
    } else {
        LaunchedEffect(!tasksFeature.isShowMainToolbar) {
            isVisibleTopBar().value = tasksFeature.isShowMainToolbar
        }
        LaunchedEffect(!tasksFeature.isShowBottomPanel) {
            isVisibleBottomPanel().value = tasksFeature.isShowBottomPanel
        }
        tasksPage.Screen()
    }*/
}