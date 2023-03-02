package ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.filter

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.core.presentation.ui.widget.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 02.03.2023
 */
@Composable
internal fun FilterChipsWidget(
    chipsCollection: FilterChipUi.Collection,
    isVisibleFirstNote: () -> Boolean,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val topBarHeightNormal = Theme.widgetSize.topBarNormalHeight
    val topBarHeightMedium = Theme.widgetSize.topBarMediumHeight
    val chipsBackgroundHeight = statusBarHeight + topBarHeightMedium + topBarHeightNormal
    val backgroundColor =
        animateColorAsState(if (isVisibleFirstNote()) background else tertiaryContainer)

    Box(
        modifier
            .fillMaxWidth()
            .height(chipsBackgroundHeight)
            .drawBehind { drawRect(backgroundColor.value) },
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .fillMaxWidth()
        ) {

            OverscrollBehavior {
                LazyRow(
                    Modifier
                        .weight(1f)
                        .padding(start = 18.dp, end = dp12, bottom = dp12)
                        .clip(Shape.cornerBig),
                    horizontalArrangement = Arrangement.spacedBy(dp8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(chipsCollection.data, key = { chip -> chip.id }) { item ->

                        FilterChipItem(item, isVisibleFirstNote, send)
                    }
                }
            }

            AddNewFilterButton(Modifier.padding(end = dp16))
        }
        FiltersOverflowCircleMask({ backgroundColor.value })
    }
}
