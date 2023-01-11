package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.maksonic.beresta.screen.main.R
import ru.maksonic.beresta.screen.main.ui.PageItem
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.widget.functional.animateAlignmentAsState
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 24.12.2022
 */
@Composable
fun TabsWidget(currentPage: Int, slidePage: (page: Int) -> Unit, modifier: Modifier) {
    val tabBarHeight = 35.dp
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
    val alignmentFromTabState by animateAlignmentAsState(setAlignment(currentPage))

    Box(
        modifier
            .fillMaxWidth(0.5f)
            .height(tabBarHeight)
            .clip(Shape.cornerRound)
            .background(secondaryContainer)
            .noRippleClickable { setCurrentPage(currentPage, slidePage) },
        contentAlignment = alignmentFromTabState
    ) {
        Box(
            modifier
                .fillMaxWidth(0.5f)
                .height(tabBarHeight)
                .clip(Shape.cornerRound)
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
}

private fun setCurrentPage(currentPage: Int, slidePage: (page: Int) -> Unit) {
    val page = if (currentPage == PageItem.NOTES.pageValue) PageItem.TASKS.pageValue
    else PageItem.NOTES.pageValue
    slidePage(page)

}

private fun setAlignment(page: Int): Alignment =
    if (page == PageItem.NOTES.pageValue) Alignment.CenterStart else Alignment.CenterEnd

private fun setTabTitleColor(page: Int, current: Color, target: Color): Color =
    if (page == PageItem.NOTES.pageValue) current else target