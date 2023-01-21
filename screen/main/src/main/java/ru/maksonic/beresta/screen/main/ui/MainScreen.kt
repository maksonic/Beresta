package ru.maksonic.beresta.screen.main.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.screen.main.ui.core.MainSandbox
import ru.maksonic.beresta.screen.main.ui.core.Screen
import ru.maksonic.beresta.screen.main.ui.widget.MainPager
import ru.maksonic.beresta.screen.main.ui.widget.MainTopBar
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 15.12.2022
 */
typealias SendMessage = (Screen.Msg) -> Unit

@Composable
fun MainScreen(sandbox: MainSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsState().value
    MainScreenContent(model = model, msg = sandbox::sendMsg)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    model: Screen.Model,
    msg: SendMessage,
) {
    val mainPagerState = rememberPagerState()
    val notesSharedState = model.notesListFeature.state.collectAsState().value
    val tasksSharedState = model.tasksListFeature.state.collectAsState().value
    val bottomPanelSharedState = model.bottomPanelFeature.state.state.collectAsState().value

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
                    targetValue = if (model.isColoredTopBar) tertiaryContainer else background
                )

            SystemStatusBar(changeableBackgroundColor = { topBarColor.value })
            // TODO: Check count of recomposition.
            MainTopBar(
                msg = msg,
                pagerState = mainPagerState,
                backgroundColor = { topBarColor.value },
                isVisible = { model.isVisibleTopBar },
                isSelectionState = { bottomPanelSharedState.selectedCount > 0})

            MainPager(
                msg = msg,
                pagerState = mainPagerState,
                notes = Pair({ model.notesListFeature.Screen() }, notesSharedState),
                tasks = Pair({ model.tasksListFeature.Screen() }, tasksSharedState),
            )
        }
        Column {
            val systemNavBarColor =
                if (model.isVisibleBottomBar) tertiaryContainer else background
            val animatedSystemNavBarColor by animateColorAsState(targetValue = systemNavBarColor)
            val bottomPanelTransition =
                animateDpAsState(
                    targetValue = if (model.isVisibleBottomBar) 0.dp
                    else
                        Theme.widgetSize.bottomPanelHeightDefault,
                )
            val bottomPanelAlpha =
                animateFloatAsState(targetValue = if (model.isVisibleBottomBar) 1f else 0f)

            model.bottomPanelFeature.Widget(
                modifier = modifier.graphicsLayer {
                    alpha = bottomPanelAlpha.value
                    translationY = bottomPanelTransition.value.toPx()
                })
            SystemNavigationBar(changeableBackgroundColor = { animatedSystemNavBarColor })
        }
    }
}
