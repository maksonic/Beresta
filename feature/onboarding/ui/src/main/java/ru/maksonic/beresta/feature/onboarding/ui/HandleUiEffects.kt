package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.onboarding.ui.core.Feature
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 15.12.2022
 */
private const val LAST_PAGE = 1

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun HandleUiEffects(
    effects: Flow<Feature.Eff>,
    pagerState: PagerState,
    onGoogleAuthClicked: () -> Unit,
    router: OnboardingRouter
) {
    val scope = rememberCoroutineScope()
    val slideDuration = Theme.dimen.durationAnimOnboarding
    val pageWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val pageWidthPx = with(LocalDensity.current) { pageWidthDp.toPx() }

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Feature.Eff.SlideNextPage -> {
                val slidePage = pageWidthPx + pagerState.pageCount

                scope.launch {
                    if (pagerState.currentPage != pagerState.pageCount - LAST_PAGE) {
                        if (pagerState.isScrollInProgress) {
                            return@launch
                        } else {
                            pagerState.animateScrollBy(value = slidePage, tween(slideDuration))
                        }
                    } else {
                        onGoogleAuthClicked.invoke()
                    }
                }
            }
            is Feature.Eff.NavigateToMain -> router.toMain()
        }
    }
}