package ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.SortDataKey
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.rippleClickable

/**
 * @Author maksonic on 06.07.2023
 */
@Composable
internal fun OrderSelector(
    key: SortDataKey,
    onOrderClicked: (Order) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(dp16, dp8, dp16, dp16)
    ) {

        Item(
            key = key,
            order = Order.ASCENDING,
            title = text.sortSheet.hintSortByAscending,
            modifier = modifier.weight(1f),
            onOrderClicked = onOrderClicked
        )

        Spacer(modifier.width(dp16))

        Item(
            key = key,
            order = Order.DESCENDING,
            title = text.sortSheet.hintSortByDescending,
            modifier = modifier.weight(1f),
            onOrderClicked = onOrderClicked
        )
    }
}

@Composable
private fun Item(
    key: SortDataKey,
    order: Order,
    title: String,
    modifier: Modifier,
    onOrderClicked: (Order) -> Unit
) {
    val value = with(listUiSortState) {
        when (key) {
            SortDataKey.NOTES -> notes
            SortDataKey.HIDDEN_NOTES -> hiddenNotes
            SortDataKey.FOLDERS -> folders
        }
    }
    val isSelected = rememberUpdatedState(order == value.order)
    val color = if (isSelected.value) tertiaryContainer else outline
    val borderWidth = if (isSelected.value) 2.dp else 1.dp

    Box(
        modifier
            .fillMaxSize()
            .height(48.dp)
            .clip(Theme.shape.cornerBig)
            .border(borderWidth, color, Theme.shape.cornerBig)
            .rippleClickable(rippleColor = primary) { onOrderClicked(order) },
        contentAlignment = Alignment.Center
    ) {
        val style = if (isSelected.value) TextDesign.bodyPrimaryMedium else TextDesign.bodyPrimary
        Text(text = title, style = style)
    }
}