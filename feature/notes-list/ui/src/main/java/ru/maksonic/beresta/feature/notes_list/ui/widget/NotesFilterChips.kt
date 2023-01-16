package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
private fun NotesFilterChipsPreview() {
    BerestaTheme {
        val previewData = remember { mutableStateOf(NoteUi.Companion.Preview.filters) }
        NotesFilterChips(filters = previewData, isVisibleFirstNote = { false })
    }
}

@Composable
internal fun NotesFilterChips(
    modifier: Modifier = Modifier,
    isVisibleFirstNote: () -> Boolean,
    filters: MutableState<List<NoteUi.Filter>>,
) {
    val lazyRowState = rememberLazyListState()
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int ->
        selectedIndex = index
    }
    val backgroundColor = animateColorAsState(
        targetValue = if (isVisibleFirstNote()) background else tertiaryContainer
    )

    Row(
        modifier
            .fillMaxWidth()
            .noRippleClickable { }
            .height(Theme.widgetSize.topBarNormalHeight)
            .drawBehind { drawRect(backgroundColor.value) }
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
                itemsIndexed(filters.value,
                    key = { _, filter -> filter.id }
                ) { index, filter ->
                    ChipItem(
                        chipFilter = filter,
                        index = index,
                        selected = selectedIndex == index,
                        onChipClick = onItemClick,
                        isVisibleFirstNote = isVisibleFirstNote
                    )
                }
            }
        }
        Surface(
            elevation = Theme.elevation.dp2,
            shape = Theme.shape.cornerNormal,
            color = transparent
        ) {
            val btnColor = animateColorAsState(
                targetValue = if (isVisibleFirstNote()) primaryContainer else tertiary
            )

            Box(
                modifier
                    .clickAction { }
                    .drawBehind { drawRect(btnColor.value) }
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
