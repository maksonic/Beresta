package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.screen.main.ui.widget.MainPager
import ru.maksonic.beresta.screen.main.ui.widget.MainTopBar
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 15.12.2022
 */
@Preview
@Composable
private fun MainScreenPreview() {
    BerestaTheme {
        MainScreenContent(systemUiController = rememberSystemUiController())
    }
}

@Composable
fun MainScreen(systemUiController: SystemUiController) {
    MainScreenContent(systemUiController = systemUiController)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    systemUiController: SystemUiController,
    bottomPanel: BottomPanelFeature = get(),
    notesPage: NotesListFeature = get(),
    tasksPage: TasksListFeature = get(),
) {
    val mainPagerState = rememberPagerState()
    //   val topBarVisibility = rememberSaveable { mutableStateOf(true) }
    val isVisibleFirstNote = rememberSaveable { mutableStateOf(true) }
    // val notesFeature = notesPage.state.collectAsState().value
    val scope = rememberCoroutineScope()
    val notesFeature = notesPage.state.collectAsState().value

    val changingBackgroundColor = if (isVisibleFirstNote.value) background else tertiaryContainer
    val topBarColor by animateColorAsState(targetValue = changingBackgroundColor)

    LaunchedEffect(!notesFeature.isVisibleFirstNote) {
        isVisibleFirstNote.value = notesFeature.isVisibleFirstNote
    }

  /* SideEffect {
        systemUiController.setStatusBarColor(color = changingBackgroundColor)
    }*/

    /*SideEffect {
        systemUiController.setStatusBarColor(color = changingBackgroundColor)
    }*/

    // val scope = rememberCoroutineScope()

    /* SideEffect {
         colorChecker.value = notesFeature.isTopListScrollState
     }*/
    /*LaunchedEffect(notesPage.state.value.isTopListScrollState) {
        Log.e("AAA", "${notesFeature.value.isTopListScrollState}")
        colorChecker.value = notesFeature.value.isTopListScrollState
    }*/
    /* SideEffect {
        scope.launch {
            notesPage.state.collect {
                colorChecker.value = it.isTopListScrollState
            }
        }
     }*/
    /* val animatedBackgroundColor by animateColorAsState(
         targetValue = if (isNotesListTopScrollState.value) background else tertiaryContainer
     )*/

    Box(
        modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier.fillMaxSize()) {
            SystemStatusBar(modifier)
            MainTopBar(
                currentPage = mainPagerState.currentPage,
                slidePage = { page ->
                    scope.launch {
                        mainPagerState.animateScrollToPage(page)
                    }
                },
                backgroundColor = { topBarColor },
                modifier = modifier
            )

            MainPager(mainPagerState, notesPage, tasksPage)
        }
        bottomPanel.Widget()
    }
}