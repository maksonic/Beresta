package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppWidgetSize = staticCompositionLocalOf<AppWidgetSize> {
    error("No component size provided")
}

data class AppWidgetSize(
    val btnNav: Dp,
    val btnPrimaryHeight: Dp,
    val topBarNormalHeight: Dp,
    val topBarMediumHeight: Dp,
    val topBarSmallHeight: Dp,
    val topBarLargeCollapsedHeight: Dp,
    val bottomBarNormalHeight: Dp,
    val bottomMainBarHeight: Dp,
    val filterChipHeight: Dp,
    val minimumTouchTargetSize: Dp,
    val modalSheetItemHeight: Dp,
    val searchBarCollapsedHeight: Dp,
    val btnFabSize: Dp,
    val noteChipsContainerHeight: Dp
)

val widgetsSize = AppWidgetSize(
    btnNav = 24.dp,
    btnPrimaryHeight = 56.dp,
    topBarNormalHeight = 56.dp,
    topBarMediumHeight = 80.dp,
    topBarSmallHeight = 64.dp,
    topBarLargeCollapsedHeight = 64.dp,
    bottomBarNormalHeight = 56.dp,
    bottomMainBarHeight = 80.dp,
    filterChipHeight = 32.dp,
    minimumTouchTargetSize = 48.dp,
    modalSheetItemHeight = 48.dp,
    searchBarCollapsedHeight = 48.dp,
    btnFabSize = 56.dp,
    noteChipsContainerHeight = 56.dp
)