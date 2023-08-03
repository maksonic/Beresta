package ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.maksonic.beresta.feature.sorting_sheet.api.Sort
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.rippledClick

/**
 * @Author maksonic on 06.07.2023
 */
@Composable
internal fun SortItem(
    item: Pair<String, Sort>,
    selected: Boolean,
    onSelect: (Sort) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.minimumTouchTargetSize)
            .padding(start = dp16, end = dp16)
            .clip(Shape.cornerSmall)
            .rippledClick(rippleColor = primary) { onSelect(item.second) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onSelect(item.second) },
            colors = RadioButtonDefaults.colors(
                selectedColor = tertiaryContainer,
                unselectedColor = outline
            ),
        )
        Text(text = item.first, style = TextDesign.bodyPrimary)
    }
}