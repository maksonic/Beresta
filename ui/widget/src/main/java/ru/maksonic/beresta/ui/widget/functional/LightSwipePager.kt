package ru.maksonic.beresta.ui.widget.functional

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi

/**
 * @Author maksonic on 15.12.2022
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalSnapperApi::class)
@Composable
fun LightSwipePager(
    modifier: Modifier = Modifier,
    pagesCount: Int,
    pagerState: PagerState,
    content: @Composable PagerScope.(page: Int) -> Unit
) {
    OverscrollBehavior {
        HorizontalPager(
            count = pagesCount,
            state = pagerState,
            flingBehavior = rememberFlingBehaviorMultiplier(
                baseFlingBehavior = PagerDefaults.flingBehavior(pagerState)
            ),
            modifier = modifier
        ) { page ->
            content(page)
        }
    }
}

private class FlingBehaviourMultiplier(
    private val multiplier: Float,
    private val baseFlingBehavior: FlingBehavior
) : FlingBehavior {
    override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
        return with(baseFlingBehavior) {
            performFling(initialVelocity * multiplier)
        }
    }
}

@Composable
private fun rememberFlingBehaviorMultiplier(
    multiplier: Float = 5f,
    baseFlingBehavior: FlingBehavior
): FlingBehavior = remember(multiplier, baseFlingBehavior) {
    FlingBehaviourMultiplier(multiplier, baseFlingBehavior)
}