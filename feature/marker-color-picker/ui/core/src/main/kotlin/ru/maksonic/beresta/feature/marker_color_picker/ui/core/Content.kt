package ru.maksonic.beresta.feature.marker_color_picker.ui.core

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_kit.animation.OverscrollBehavior
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogPrimary
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogSecondary
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.format.FormatResetColor
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiState
import ru.maksonic.beresta.feature.marker_color_picker.ui.core.widget.ColorPickerTabRow
import ru.maksonic.beresta.feature.marker_color_picker.ui.core.widget.ColorsGrid
import ru.maksonic.beresta.feature.marker_color_picker.ui.core.widget.PageFadingEdge
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 09.09.2023
 */
private const val INITIAL_COLOR_ID = 0L
private const val INITIAL_PAGER_PAGE = 1
private const val MARKERS_PAGES_COUNT = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    state: MarkerPickerUiState,
    onColorClicked: (Long) -> Unit,
    hideDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    var fadeEdgeHeight by remember { mutableStateOf(0.dp) }
    val currentColor = rememberSaveable { mutableLongStateOf(state.currentSelectedColorId) }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = INITIAL_PAGER_PAGE,
        initialPageOffsetFraction = 0f,
        pageCount = { MARKERS_PAGES_COUNT }
    )

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextHeadlineSmall(
            text = text.editor.dialogTitleMarkerColorPicker,
            modifier = modifier.padding(top = dp24, bottom = dp16)
        )

        ColorPickerTabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = modifier,
            onTabClicked = { scope.launch { pagerState.animateScrollToPage(it) } }
        )

        Box(
            modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    fadeEdgeHeight = with(density) { it.size.height.toDp() }
                }) {

            OverscrollBehavior {

                HorizontalPager(
                    state = pagerState,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = dp16)
                ) { page ->
                    ColorsGrid(
                        page = page,
                        colors = state.colors,
                        currentSelectedColorId = currentColor.longValue,
                        onColorClicked = {
                            currentColor.longValue = if (currentColor.longValue == it) 0L else it
                        }
                    )
                }
            }

            PageFadingEdge(modifier, fadeEdgeHeight)
        }
        Row(
            Modifier.padding(top = dp24, bottom = dp24, start = dp16, end = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val backgroundColor = rememberUpdatedState(
                if (currentColor.longValue == 0L) onSecondaryContainer else secondaryContainer
            )
            ButtonIcon(
                icon = AppIcon.FormatResetColor,
                onClick = { pagerState.currentPage.onResetBtnClicked(state.colors, currentColor) },
                modifier = Modifier
                    .clip(CircleShape)
                    .drawBehind { drawRect(backgroundColor.value) }
                    .size(40.dp)
            )

            ButtonDialogSecondary(
                title = text.shared.btnTitleCancel,
                onClick = hideDialog,
                modifier = Modifier.weight(1f)
            )
            ButtonDialogPrimary(
                title = text.shared.btnTitleAccept,
                onClick = {
                    onColorClicked(currentColor.longValue).run { hideDialog() }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun Int.onResetBtnClicked(colors: List<ColorContainer>, currentColor: MutableLongState) =
    if (currentColor.longValue != INITIAL_COLOR_ID)
        currentColor.longValue = INITIAL_COLOR_ID
    else {
        currentColor.longValue = colors.first { it.categoryId == this }.colorId
    }
