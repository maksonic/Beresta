package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*

/**
 * @Author maksonic on 26.12.2022
 */
@Composable
fun LazyListState.isVisibleFirstItem(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemIndex == 0 } }
}

@Composable
fun LazyListState.isVisibleFirstItemOffset(): State<Boolean> {
    val isFirstItemInList = layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0

    return if (isFirstItemInList) {
        remember { derivedStateOf { layoutInfo.visibleItemsInfo.firstOrNull()?.offset == 0 } }
    } else {
        remember { derivedStateOf { false } }
    }
}

@Composable
fun LazyListState.isVisibleLastItemOffset(): State<Boolean> {
    val isLastItemInList =
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    return if (isLastItemInList) {
        val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
        remember {
            derivedStateOf {
                lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
            }
        }
    } else {
        remember { derivedStateOf { false } }
    }
}

@Composable
fun LazyListState.isScrollUp(): State<Boolean> {
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
    }
}