package ru.maksonic.beresta.feature.sorting_sheet.ui.ui.widget

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.sorting_sheet.api.Order
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 06.07.2023
 */
@Composable
internal fun OrderSelector(
    onOrderClicked: (Order) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16, bottom = dp16)
    ) {

        Item(
            order = Order.ASCENDING,
            title = "По порядку",
            modifier = modifier.weight(1f),
            onOrderClicked = onOrderClicked
        )

        Spacer(modifier.width(dp16))

        Item(
            order = Order.DESCENDING,
            title = "В обратном порядке",
            modifier = modifier.weight(1f),
            onOrderClicked = onOrderClicked
        )
    }
}

@Composable
private fun Item(
    order: Order,
    title: String,
    modifier: Modifier,
    onOrderClicked: (Order) -> Unit
) {
    val isSelected = rememberUpdatedState(order == listUiSortState.order)

    BoxWithScaleInOutOnClick(
        onClick = { onOrderClicked(order) },
        modifier = modifier.height(Theme.widgetSize.minimumTouchTargetSize)
    ) {
        val borderColor = animateColorAsState(if (isSelected.value) primary else outline)
        val backgroundColor =
            animateColorAsState(if (isSelected.value) onSecondaryContainer else secondaryContainer)
        Box(
            Modifier
                .fillMaxSize()
                .clip(Theme.shape.cornerBig)
                .drawBehind { drawRect(backgroundColor.value) }
                .border(1.dp, borderColor.value, Theme.shape.cornerBig),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title, style = TextDesign.bodyPrimary)
        }
    }
}