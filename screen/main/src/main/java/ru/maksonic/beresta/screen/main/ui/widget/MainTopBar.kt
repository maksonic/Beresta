package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.screen.main.ui.core.Screen
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 17.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainTopBar(
    msg: SendMessage,
    pagerState: PagerState,
    backgroundColor: () -> Color,
    isVisible: () -> Boolean,
    isSelectionState: () -> Boolean,
    modifier: Modifier = Modifier,
) {
    val topTabTransition = animateDpAsState(
        targetValue = if (isVisible()) 0.dp else -Theme.widgetSize.bottomPanelHeightDefault
    )

    val topBarHeight = animateDpAsState(
        targetValue = if (isVisible()) Theme.widgetSize.topBarNormalHeight else 0.dp,
    )

    Row(
        modifier
            .fillMaxWidth()
            .height(topBarHeight.value)
            .drawBehind { drawRect(backgroundColor()) }
            .graphicsLayer {
                translationY = topTabTransition.value.toPx()
            }, verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconAction(
            icon = painterResource(id = ru.maksonic.beresta.ui.theme.R.drawable.ic_settings),
            action = { msg(Screen.Msg.Ui.OnSettingsClicked) },
            modifier = modifier.padding(start = dp8)
        )
        TabsWidget(pagerState, modifier)

        ShareSelectedNotesActionButton(msg, isSelectionState())
    }
}

@Composable
fun ShareSelectedNotesActionButton(
    msg: SendMessage,
    isSelectionState: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(end = dp8)
            .size(Theme.widgetSize.minimumTouchTargetSize),
        contentAlignment = Alignment.Center
    ) {

        AnimateFadeInOut(visible = isSelectionState) {
            val iconRotation = remember { Animatable(-180f) }

            LaunchedEffect(isSelectionState) {
                iconRotation.animateTo(0f)
            }

            IconAction(
                icon = painterResource(id = ru.maksonic.beresta.ui.theme.R.drawable.ic_share),
                action = { msg(Screen.Msg.Ui.OnShareSelectedNotes) },
                modifier = modifier
                    .size(Theme.widgetSize.minimumTouchTargetSize)
                    .graphicsLayer {
                        rotationZ = iconRotation.value
                    }
            )
        }
    }
}

@Preview
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainTopBarPreview() {
    BerestaTheme {
        val bg = background
        MainTopBar(
            msg = {},
            pagerState = rememberPagerState(),
            backgroundColor = { bg },
            isVisible = { true }, isSelectionState = { true })
    }
}
