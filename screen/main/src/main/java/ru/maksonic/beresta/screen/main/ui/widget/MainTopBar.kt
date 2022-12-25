package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.maksonic.beresta.screen.main.R
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.Shape.cornerRound
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animateAlignmentAsState
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 17.12.2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun MainTopBar(
    pagerState: PagerState,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically() + expandVertically(),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarNormalHeight)
                .background(background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconAction(
                icon = painterResource(id = ru.maksonic.beresta.ui.theme.R.drawable.ic_settings),
                action = {},
                modifier = modifier.padding(start = dp8)
            )
            TabsWidget(pagerState = pagerState, modifier)
            Spacer(
                modifier
                    .size(Theme.widgetSize.minimumTouchTargetSize)
                    .padding(end = dp8)
            )
        }
    }
}
