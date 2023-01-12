package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.screen.main.ui.widget.MainPager
import ru.maksonic.beresta.screen.main.ui.widget.MainTopBar
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
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
    val notesFeature = notesPage.state.collectAsState().value
    val isVisibleFirstNote = rememberSaveable { mutableStateOf(true) }
    val isVisibleBottomPanel = rememberSaveable { mutableStateOf(true) }
    val topBarColor = if (isVisibleFirstNote.value) background else tertiaryContainer
    val animatedTopBarColor by animateColorAsState(targetValue = topBarColor)
    val systemNavBarColor = if (isVisibleBottomPanel.value) tertiaryContainer else background
    val animatedSystemNavBarColor by animateColorAsState(targetValue = systemNavBarColor)

    LaunchedEffect(!notesFeature.isTopScrollState) {
        isVisibleFirstNote.value = notesFeature.isTopScrollState
    }
    LaunchedEffect(notesFeature.isScrollUp) {
        isVisibleBottomPanel.value = notesFeature.isScrollUp
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
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier.fillMaxSize()) {
            SystemStatusBar(changeableBackgroundColor = { animatedTopBarColor })
            MainTopBar(
                pagerState = mainPagerState,
                backgroundColor = { animatedTopBarColor },
                modifier = modifier
            )
            MainPager(pagerState = { mainPagerState }, notesPage, tasksPage)
        }
        Column {
            /*AnimatedVisibility(visible = isVisibleBottomPanel.value) {
                bottomPanel.Widget()
            }*/
            bottomPanel.Widget()
            SystemNavigationBar(changeableBackgroundColor = { animatedSystemNavBarColor })
        }
    }
}