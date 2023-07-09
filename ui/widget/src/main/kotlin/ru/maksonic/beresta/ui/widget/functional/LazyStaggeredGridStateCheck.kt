package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.*

/**
 * @Author maksonic on 26.03.2023
 */
@Composable
fun LazyStaggeredGridState.isVisibleFirstItem(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemIndex == 0 } }
}

@Composable
fun LazyStaggeredGridState.isVisibleFirstItemOffset(): State<Boolean> {
    return remember { derivedStateOf { this.firstVisibleItemScrollOffset == 0 } }
}

@Composable
fun LazyStaggeredGridState.isScrollUp(): State<Boolean> {
    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
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