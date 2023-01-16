package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch
import ru.maksonic.beresta.screen.main.R
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.widget.functional.animateAlignmentAsState
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
import ru.maksonic.beresta.ui.widget.functional.tintSimply

/**
 * @Author maksonic on 24.12.2022
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsWidget(pagerState: PagerState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage
    val tabAlignment = if (isNoteTab(currentPage)) Alignment.CenterStart else Alignment.CenterEnd
    val selectTabWithAlignmentAnimation by animateAlignmentAsState(tabAlignment)
    val firstTabTitleColor = animateColorAsState(
        targetValue = setTabTitleColor(
            page = currentPage,
            current = onPrimary,
            target = onSecondaryContainer
        )
    )
    val secondTabTitleColor = animateColorAsState(
        targetValue = setTabTitleColor(
            page = currentPage,
            current = onSecondaryContainer,
            target = onPrimary
        )
    )

    Box(
        modifier
            .fillMaxWidth(0.5f)
            .height(Theme.widgetSize.tabBarHeight)
            .clip(Shape.cornerRound)
            .background(secondaryContainer)
    ) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = selectTabWithAlignmentAnimation
        ) {
            Box(
                modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
                    .clip(Shape.cornerRound)
                    .background(primary)
            )
        }

        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(R.string.title_tab_notes),
                style = TextDesign.title,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .weight(1f)
                    .tintSimply(firstTabTitleColor)
                    .noRippleClickable {
                        scope.launch {
                            pagerState.animateScrollToPage(PageItem.NOTES.pageValue)
                        }
                    }
            )
            Text(
                stringResource(R.string.title_tab_tasks),
                style = TextDesign.title,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .weight(1f)
                    .tintSimply(secondTabTitleColor)
                    .noRippleClickable {
                        scope.launch {
                            pagerState.animateScrollToPage(PageItem.TASKS.pageValue)
                        }
                    })
        }
    }
}

private fun isNoteTab(currentPage: Int): Boolean {
    return currentPage == PageItem.NOTES.pageValue
}

private fun setTabTitleColor(page: Int, current: Color, target: Color): Color =
    if (isNoteTab(page)) current else target
























