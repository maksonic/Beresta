package ru.maksonic.beresta.feature.marker_color_picker.ui.core.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import ru.maksonic.beresta.common.ui_kit.widget.marker_color_picker.SelectableCircleItem
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.ColorContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 11.09.2023
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ColorsGrid(
    page: Int,
    colors: List<ColorContainer>,
    currentSelectedColorId: Long,
    onColorClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val screenOrientation = LocalConfiguration.current.orientation
    val maxItemsInEachRow = if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) 4 else 8
    val data = remember { mutableStateOf(colors.filter { it.categoryId == page }) }

    FlowRow(
        modifier
            .fillMaxWidth()
            .padding(start = dp8, end = dp8),
        maxItemsInEachRow = maxItemsInEachRow,
    ) {
        data.value.forEach { color ->
            SelectableCircleItem(
                primaryColor = color.value,
                isSelected = color.colorId == currentSelectedColorId,
                onClick = { onColorClicked(color.colorId)},
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(top = dp16, start = dp8, end = dp8)
                    .size(Theme.size.minimumTouchTargetSize)
                    .clip(CircleShape)

            )
        }
    }
}