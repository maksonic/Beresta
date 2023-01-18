package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.screen.main.ui.core.Screen
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction

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
    modifier: Modifier = Modifier,
) {
    val topTabTransition = animateDpAsState(
        targetValue = if (isVisible()) 0.dp else -Theme.widgetSize.bottomPanelHeightIdle
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
        Spacer(
            modifier
                .size(Theme.widgetSize.minimumTouchTargetSize)
                .padding(end = dp8)
        )
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
            isVisible = { true })
    }
}
