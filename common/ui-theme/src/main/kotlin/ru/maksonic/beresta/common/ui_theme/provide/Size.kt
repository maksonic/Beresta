package ru.maksonic.beresta.common.ui_theme.provide

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 08.11.2022
 */
val LocalAppSize = staticCompositionLocalOf<AppSize> {
    error("No component size provided")
}

data class AppSize(
    val btnNav: Dp,
    val btnPrimaryHeight: Dp,
    val topBarNormalHeight: Dp,
    val topBarMediumHeight: Dp,
    val topBarSmallHeight: Dp,
    val topBarLargeCollapsedHeight: Dp,
    val bottomBarNormalHeight: Dp,
    val bottomMainBarHeight: Dp,
    val chipHeight: Dp,
    val minimumTouchTargetSize: Dp,
    val modalSheetItemHeightLarge: Dp,
    val modalSheetItemHeightNormal: Dp,
    val modalSheetItemHeightSmall: Dp,
    val searchBarCollapsedHeight: Dp,
    val btnFabSize: Dp,
    val listItemContainerHeight: Dp,
    val noteChipsContainerHeight: Dp,
)

val sizes = AppSize(
    btnNav = 24.dp,
    btnPrimaryHeight = 56.dp,
    topBarNormalHeight = 56.dp,
    topBarMediumHeight = 80.dp,
    topBarSmallHeight = 64.dp,
    topBarLargeCollapsedHeight = 64.dp,
    bottomBarNormalHeight = 56.dp,
    bottomMainBarHeight = 80.dp,
    chipHeight = 32.dp,
    minimumTouchTargetSize = 48.dp,
    modalSheetItemHeightLarge = 68.dp,
    modalSheetItemHeightNormal = 56.dp,
    modalSheetItemHeightSmall = 48.dp,
    searchBarCollapsedHeight = 48.dp,
    btnFabSize = 56.dp,
    listItemContainerHeight = 56.dp,
    noteChipsContainerHeight = 56.dp
)