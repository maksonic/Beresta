package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp6

/**
 * @Author maksonic on 02.03.2023
 */
@Composable
internal fun FiltersOverflowCircleMask(backgroundColor: () -> Color, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.filterChipHeight.plus(24.dp))
            .padding(start = dp6, end = 52.dp)
            .clip(CircleShape)
            .border(12.dp, backgroundColor(), CircleShape)
    )
}