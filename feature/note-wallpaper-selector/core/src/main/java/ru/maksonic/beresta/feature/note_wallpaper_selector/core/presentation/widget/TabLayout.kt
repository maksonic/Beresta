package ru.maksonic.beresta.feature.note_wallpaper_selector.core.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.note_wallpaper_selector.core.data.WallpaperCategory
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.tab.TabWithoutRipple

/**
 * @Author maksonic on 10.03.2023
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun TabLayout(
    wallpapers: WallpaperCategory.Collection,
    pagerState: () -> PagerState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        backgroundColor = transparent,
        contentColor = onBackground,
        selectedTabIndex = pagerState().currentPage,
        divider = { Spacer(modifier = modifier.height(5.dp)) },
        edgePadding = dp16,
        indicator = { tabPositions -> TabIndicator(pagerState, tabPositions, modifier) },
    ) {

        wallpapers.data.forEachIndexed { index, page ->
            TabWithoutRipple(
                text = { Text(text = page.title) },
                selected = pagerState().currentPage == index,
                selectedContentColor = primary,
                unselectedContentColor = secondary,
                onClick = {
                    scope.launch {
                        pagerState().animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabIndicator(
    pagerState: () -> PagerState,
    tabPositions: List<TabPosition>,
    modifier: Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .pagerTabIndicatorOffset(pagerState(), tabPositions)
            .height(dp4)
            .padding(start = dp8, end = dp8)
            .clip(CircleShape)
            .background(primary)
    )
}