package ru.maksonic.beresta.feature.notes_list.ui.widget

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 25.12.2022
 */
/*@Preview
@Composable
fun NotesFilterChipsPreview() {
    BerestaTheme {
        NotesFilterChips(
            filters = NoteUi.defaultFilters(),
            *//*isScrolledTop = true,
            onFilterClick = {},*//*
            isTopScrollState = remember {
                mutableStateOf(false)
            })
    }
}*/

@Composable
internal fun NotesFilterChips(
    modifier: Modifier = Modifier,
    filters: List<NoteUi.Filter>,
    isScrolledTop: () -> Boolean
) {
    val lazyRowState = rememberLazyListState()
    val context = LocalContext.current
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int ->
        selectedIndex = index
        Toast.makeText(context, index.toString(), Toast.LENGTH_SHORT).show()
    }
    val backgroundColor = if (isScrolledTop()) background else tertiaryContainer

    OverscrollBehavior {
        LazyRow(
            state = lazyRowState,
            modifier = modifier
                .noRippleClickable { }
                .graphicsLayer {
                    shape = RoundedCornerShape(bottomStart = 16.dp.toPx(), bottomEnd = 16.dp.toPx())
                    clip = true
                }
                .drawBehind { drawRect(backgroundColor) }
                .padding(start = dp16, end = dp16, bottom = dp8)
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarNormalHeight),
            horizontalArrangement = Arrangement.spacedBy(dp8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(filters) { filter ->
                ChipItem(chipFilter = filter, isScrolledTop = isScrolledTop)
            }
        }
    }
}
