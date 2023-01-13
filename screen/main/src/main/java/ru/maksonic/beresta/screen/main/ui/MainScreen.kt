package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature
import ru.maksonic.beresta.screen.main.ui.widget.MainPager
import ru.maksonic.beresta.screen.main.ui.widget.MainTopBar
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
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
        MainScreenContent()
    }
}

@Composable
fun MainScreen() {
    MainScreenContent()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
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
    val bottomPanelTransition =
        animateDpAsState(
            targetValue = if (isVisibleBottomPanel.value) 0.dp
            else
                Theme.widgetSize.bottomPanelHeightIdle,
        )
    val bottomPanelAlpha =
        animateFloatAsState(targetValue = if (isVisibleBottomPanel.value) 1f else 0f)


    LaunchedEffect(notesFeature.isTopScrollState) {
        isVisibleFirstNote.value = notesFeature.isTopScrollState
    }
    LaunchedEffect(notesFeature.isVisibleBottomPanel) {
        isVisibleBottomPanel.value = notesFeature.isVisibleBottomPanel
    }

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
            bottomPanel.Widget(modifier.graphicsLayer {
                alpha = bottomPanelAlpha.value
                translationY = bottomPanelTransition.value.toPx()
            })
            SystemNavigationBar(changeableBackgroundColor = { animatedSystemNavBarColor })
        }
    }
}