package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

/**
 * @Author maksonic on 26.12.2022
 */
@Composable
fun LazyListState.isVisibleFirstItem(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemIndex == 0  } }
}

@Composable
fun LazyListState.isVisibleFirstItemOffset(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemScrollOffset == 0  } }
}
@Composable
fun LazyListState.isInvisibleFirstItem(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemIndex > 0  } }
}

@Composable
fun LazyListState.isScrollUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

fun LazyListState.isScrolledEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

fun LazyListState.isScrolledTop() =
    layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0