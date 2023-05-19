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
    val topBarLargeCollapsedHeight: Dp,
    val bottomBarHeightDefault: Dp,
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
    topBarLargeCollapsedHeight = 64.dp,
    bottomBarHeightDefault = 56.dp,
    bottomMainBarHeight = 80.dp,
    filterChipHeight = 32.dp,
    minimumTouchTargetSize = 48.dp,
    modalSheetItemHeight = 48.dp,
    searchBarCollapsedHeight = 48.dp,
    btnFabSize = 56.dp,
    noteChipsContainerHeight = 64.dp
)