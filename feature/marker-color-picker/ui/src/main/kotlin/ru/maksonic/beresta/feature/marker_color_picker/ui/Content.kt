package ru.maksonic.beresta.feature.marker_color_picker.ui

import androidx.compose.animation.core.spring
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerPickerState
import ru.maksonic.beresta.feature.marker_color_picker.ui.widget.ColorPickerTabRow
import ru.maksonic.beresta.feature.marker_color_picker.ui.widget.ColorsGrid
import ru.maksonic.beresta.feature.marker_color_picker.ui.widget.PageFadingEdge
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 09.09.2023
 */
private const val PAGES_COUNT = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Content(
    state: State<MarkerPickerState>,
    onSelectMarkerColorClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var fadeEdgeHeight by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f,
        pageCount = { PAGES_COUNT }
    )

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text.editNote.dialogTitleMarkerColorPicker,
            style = TextDesign.topBar,
            modifier = modifier.padding(bottom = dp16)
        )

        ColorPickerTabRow(pagerState.currentPage, modifier, onTabClicked = {
            scope.launch { pagerState.animateScrollToPage(it) }
        })

        Box(
            modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    fadeEdgeHeight = with(density) { it.size.height.toDp() }
                }) {

            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxWidth(),
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    highVelocityAnimationSpec = splineBasedDecay(LocalDensity.current),
                    snapAnimationSpec = spring(stiffness = 5000f)
                )
            ) { page ->
                ColorsGrid(
                    page = page,
                    state = state,
                    onColorClicked = onSelectMarkerColorClicked
                )
            }

            PageFadingEdge(modifier, fadeEdgeHeight)
        }
    }
}