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
    val tabBarHeight: Dp,
    val bottomPanelHeightIdle: Dp,
    val bottomPanelHeightSelected: Dp,
    val filterChipHeight: Dp,
    val minimumTouchTargetSize: Dp
)

val widgetsSize = AppWidgetSize(
    btnNav = 24.dp,
    btnPrimaryHeight = 56.dp,
    tabBarHeight = 36.dp,
    topBarNormalHeight = 56.dp,
    bottomPanelHeightIdle = 72.dp,
    bottomPanelHeightSelected = 120.dp,
    filterChipHeight = 42.dp,
    minimumTouchTargetSize = 48.dp
)