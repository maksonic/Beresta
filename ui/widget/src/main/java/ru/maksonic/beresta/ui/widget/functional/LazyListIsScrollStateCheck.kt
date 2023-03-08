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

fun LazyListState.isVisibleFirstItemOffset(): Boolean {
    val isFirstItemInList = layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0

    return if (isFirstItemInList) {
        layoutInfo.visibleItemsInfo.firstOrNull()?.offset == 0
    } else {
        false
    }
}

fun LazyListState.isVisibleLastItemOffset(): Boolean {
    val isLastItemInList =
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    return if (isLastItemInList) {
        val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
        lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
    } else {
        false
    }
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

fun LazyListState.isScrolledTop() =
    layoutInfo.visibleItemsInfo.firstOrNull()?.index == 0


