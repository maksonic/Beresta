package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior

/**
 * @Author maksonic on 20.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainPager(
    pagerState: PagerState,
    notesPage: NotesListFeature,
    tasksPage: TasksListFeature,
    modifier: Modifier = Modifier
) {
    OverscrollBehavior {
        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            Page(
                page = page,
                notesPage = notesPage,
                tasksPage = tasksPage,
            )
        }
    }
}

@Composable
internal fun Page(
    notesPage: NotesListFeature,
    tasksPage: TasksListFeature,
    page: Int,
) {
    if (page == PageItem.NOTES.pageValue) {
        notesPage.Screen()
    } else {
        tasksPage.Screen()
    }
}