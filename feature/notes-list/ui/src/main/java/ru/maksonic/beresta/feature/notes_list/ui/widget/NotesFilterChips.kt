package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.clickAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 25.12.2022
 */
@Preview
@Composable
fun NotesFilterChipsPreview() {
    BerestaTheme {
        NotesFilterChips(
            filters = NoteUi.Companion.Preview.filters,
            isTopScrollState = { true }
        )
    }
}

@Composable
internal fun NotesFilterChips(
    modifier: Modifier = Modifier,
    filters: List<NoteUi.Filter>,
    isTopScrollState: () -> Boolean,
) {
    val lazyRowState = rememberLazyListState()
    val backgroundColor by animateColorAsState(
        targetValue = if (isTopScrollState()) background else tertiaryContainer
    )
    val chipBackgroundColor by animateColorAsState(
        targetValue = if (isTopScrollState()) primaryContainer else tertiary
    )
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int ->
        selectedIndex = index
    }

    Row(
        modifier
            .fillMaxWidth()
            .noRippleClickable { }
            .height(Theme.widgetSize.topBarNormalHeight)
            .graphicsLayer {
                shape =
                    RoundedCornerShape(bottomStart = 16.dp.toPx(), bottomEnd = 16.dp.toPx())
                clip = true
            }
            .drawBehind { drawRect(backgroundColor) }
            .padding(start = dp16, end = dp16, top = dp8, bottom = dp8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OverscrollBehavior {
            LazyRow(
                state = lazyRowState,
                modifier = modifier
                    .weight(1f)
                    .padding(end = dp8),
                horizontalArrangement = Arrangement.spacedBy(dp8)
            ) {
                filters.forEachIndexed { index, filter ->
                    item {
                        ChipItem(
                            chipFilter = filter, chipBackgroundColor = { chipBackgroundColor },
                            index = index,
                            selected = selectedIndex == index,
                            onChipClick = onItemClick
                        )
                    }
                }
            }
        }
        Surface(
            elevation = Theme.elevation.dp2,
            shape = Theme.shape.cornerNormal,
            color = transparent
        ) {
            Box(
                modifier
                    .clickAction { }
                    .drawBehind { drawRect(chipBackgroundColor) }
                    .size(Theme.widgetSize.filterChipHeight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = ru.maksonic.beresta.ui.theme.R.drawable.ic_folder
                    ),
                    tint = onTertiary,
                    contentDescription = ""
                )
            }
        }
    }
}
