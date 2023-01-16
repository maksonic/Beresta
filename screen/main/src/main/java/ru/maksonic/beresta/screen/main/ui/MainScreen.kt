package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
    val tasksFeature = tasksPage.state.collectAsState().value
    val isVisibleTopBar = rememberSaveable { mutableStateOf(true) }
    val isColoredTopBar = rememberSaveable { mutableStateOf(false) }
    val isVisibleBottomPanel = rememberSaveable { mutableStateOf(true) }


    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier
                .fillMaxSize()
        ) {
            val topBarColor =
                animateColorAsState(
                    targetValue = if (isColoredTopBar.value) tertiaryContainer else background
                )

            SystemStatusBar(changeableBackgroundColor = { topBarColor.value })
            // TODO: Check count of recomposition.
            MainTopBar(
                pagerState = mainPagerState,
                backgroundColor = { topBarColor.value }, isVisible = { isVisibleTopBar.value })

            MainPager(
                notesPage = notesPage,
                tasksPage = tasksPage,
                pagerState = { mainPagerState },
                notesFeature = notesFeature,
                tasksFeature = tasksFeature,
                isVisibleBottomPanel = { isVisibleBottomPanel },
                isColoredTopBar = { isColoredTopBar },
                isVisibleTopBar = { isVisibleTopBar },
            )
        }
        Column {
            val systemNavBarColor =
                if (isVisibleBottomPanel.value) tertiaryContainer else background
            val animatedSystemNavBarColor by animateColorAsState(targetValue = systemNavBarColor)
            val bottomPanelTransition =
                animateDpAsState(
                    targetValue = if (isVisibleBottomPanel.value) 0.dp
                    else
                        Theme.widgetSize.bottomPanelHeightIdle,
                )
            val bottomPanelAlpha =
                animateFloatAsState(targetValue = if (isVisibleBottomPanel.value) 1f else 0f)

            bottomPanel.Widget(modifier.graphicsLayer {
                alpha = bottomPanelAlpha.value
                translationY = bottomPanelTransition.value.toPx()
            })
            SystemNavigationBar(changeableBackgroundColor = { animatedSystemNavBarColor })
        }
    }
}