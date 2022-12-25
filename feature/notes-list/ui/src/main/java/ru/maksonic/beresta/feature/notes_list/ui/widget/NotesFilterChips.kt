package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior

/**
 * @Author maksonic on 25.12.2022
 */
@Preview
@Composable
fun NotesFilterChipsPreview() {
    BerestaTheme {
        NotesFilterChips(isScroll = false)
    }
}
@Composable
internal fun NotesFilterChips(isScroll: Boolean, modifier: Modifier = Modifier) {
    val list =
        listOf(
            "Все",
            "Просто папка",
            "Fake folder",
            "Some folder",
            "Test folder",
            "Favorites",
            "Best notes",
            "Maksonic"
        )
    var selectedIndex by remember { mutableStateOf(0) }
    val onItemClick = { index: Int -> selectedIndex = index}
    val elevationTabBar =
        if (isScroll) Theme.elevation.dp8 else Theme.elevation.disable

    OverscrollBehavior {
        LazyRow(
            modifier
                .fillMaxWidth()
                .graphicsLayer {
                    shadowElevation = elevationTabBar.toPx()
                    shape = RoundedCornerShape(bottomStart = 16.dp.toPx(), bottomEnd = 16.dp.toPx())
                    clip = true
                }
                .background(background)
                .padding(start = dp16, end = dp16, top = dp24, bottom = dp12),
            horizontalArrangement = Arrangement.spacedBy(dp8)
        ) {
            items(list.count()) { index ->
                ChipItem(
                    chipTitle = list[index],
                    index = index,
                    selected = selectedIndex == index,
                    onClick = onItemClick
                )
            }
        }
    }
}