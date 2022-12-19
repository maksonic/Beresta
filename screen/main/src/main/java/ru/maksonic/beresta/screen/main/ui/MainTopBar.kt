package ru.maksonic.beresta.screen.main.ui

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
internal fun MainTabBar(
    pagerState: PagerState,
    scope: CoroutineScope,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val tabBarHeight = 35.dp
    val currentPage = pagerState.currentPage
    val alignmentFromTabState by animateAlignmentAsState(setAlignment(currentPage))
    val firstTabTitleColor = animateColorAsState(
        targetValue = setTabTitleColor(
            currentPage,
            onPrimary,
            onSecondaryContainer
        )
    )
    val secondTabTitleColor = animateColorAsState(
        targetValue = setTabTitleColor(
            currentPage,
            onSecondaryContainer,
            onPrimary
        )
    )
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
            Box(
                modifier
                    .fillMaxWidth(0.5f)
                    .height(tabBarHeight)
                    .clip(cornerRound)
                    .background(secondaryContainer)
                    .noRippleClickable { setCurrentPage(pagerState, scope) },
                contentAlignment = alignmentFromTabState
            ) {
                Box(
                    modifier
                        .fillMaxWidth(0.5f)
                        .height(tabBarHeight)
                        .clip(cornerRound)
                        .background(primary)
                        .noRippleClickable {},
                )
                Box(
                    modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            stringResource(R.string.title_tab_notes),
                            style = TextDesign.title.copy(color = firstTabTitleColor.value)
                        )
                        Text(
                            stringResource(R.string.title_tab_tasks),
                            style = TextDesign.title.copy(color = secondTabTitleColor.value)
                        )
                    }
                }
            }
            Spacer(modifier.height(Theme.widgetSize.minimumTouchTargetSize).padding(end = dp8))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
private fun setCurrentPage(pagerState: PagerState, scope: CoroutineScope) {
    val page = if (pagerState.currentPage == PageItem.NOTES.pageValue) PageItem.TASKS.pageValue
    else PageItem.NOTES.pageValue
    scope.launch {
        pagerState.animateScrollToPage(page)
    }
}

private fun setAlignment(page: Int): Alignment =
    if (page == PageItem.NOTES.pageValue) Alignment.CenterStart else Alignment.CenterEnd

private fun setTabTitleColor(page: Int, current: Color, target: Color): Color =
    if (page == PageItem.NOTES.pageValue) current else target