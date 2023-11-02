package ru.maksonic.beresta.feature.onboarding.ui.core.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.onboarding.ui.core.Eff
import ru.maksonic.beresta.feature.onboarding.ui.core.Msg
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.beresta.navigation.router.routes.OnboardingRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 27.09.2023
 */
internal typealias Send = (Msg) -> Unit

internal const val LAST_PAGE = 3

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun Container(router: OnboardingRouter, sandbox: OnboardingSandbox = koinViewModel()) {
    val model by sandbox.model.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState { model.onboardings.count() }
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        hideSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        modalBottomSheetState = modalBottomSheetState
    )

    Content(
        model = model,
        send = sandbox::send,
        pagerState = pagerState,
        modalBottomSheetState = modalBottomSheetState
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: OnboardingRouter,
    pagerState: PagerState,
    modalBottomSheetState: SheetState,
    onGoogleAuthClicked: () -> Unit,
    hideSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scope2 = rememberCoroutineScope()
    val slideDuration = Theme.dimen.durationAnimOnboarding
    val pageWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val pageWidthPx = with(LocalDensity.current) { pageWidthDp.toPx() }

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateToMain -> router.toMain()
            is Eff.SlideNextPage -> {
                val nextPage = pageWidthPx + pagerState.currentPage

                scope.launch {
                    if (pagerState.currentPage != LAST_PAGE) {
                        if (pagerState.isScrollInProgress) {
                            return@launch
                        } else {
                            pagerState.animateScrollBy(value = nextPage, tween(slideDuration))
                        }
                    } else {
                        onGoogleAuthClicked.invoke()
                    }
                }
            }

            is Eff.HideModalSheet -> {
                scope2.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}