package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.*

/**
 * @Author maksonic on 26.03.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridState.isVisibleFirstItem(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemIndex == 0 } }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridState.isVisibleFirstItemOffset(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemScrollOffset == 0 } }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyStaggeredGridState.isScrollUp(): Boolean {
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