package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 17.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainTopBar(
    pagerState: PagerState,
    backgroundColor: () -> Color,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .drawBehind { drawRect(backgroundColor()) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconAction(
            icon = painterResource(id = ru.maksonic.beresta.ui.theme.R.drawable.ic_settings),
            action = {},
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
