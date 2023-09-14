package ru.maksonic.beresta.feature.marker_color_picker.ui.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerPickerState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 11.09.2023
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ColorsGrid(
    page: Int,
    state: State<MarkerPickerState>,
    onColorClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val screenOrientation = LocalConfiguration.current.orientation
    val maxItemsInEachRow = if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) 4 else 8

    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(start = dp8, end = dp8),
        maxItemsInEachRow = maxItemsInEachRow,
    ) {
        val data = remember { mutableStateOf(data(page)) }

        data.value.forEach { color ->
            ColorPickerItem(
                isSelected = color.key == state.value.colorId,
                color = color.value,
                onColorClicked = { onColorClicked(color.key)},
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(top = dp8, start = dp4, end = dp4)
                    .size(Theme.widgetSize.minimumTouchTargetSize)
                    .clip(CircleShape)

            )
        }
    }
}

fun data(page: Int) = when (page) {
    1 -> normalColors
    2 -> neutralColors
    else -> brightColors
}

private val brightColors = Palette.Bright.colorMap
private val normalColors = Palette.Normal.colorMap
private val neutralColors = Palette.Neutral.colorMap