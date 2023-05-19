package ru.maksonic.beresta.feature.onboarding.core

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.data.images
import ru.maksonic.beresta.feature.onboarding.core.data.onboardings
import ru.maksonic.beresta.feature.onboarding.core.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.feature.onboarding.core.widget.NextSlideButton
import ru.maksonic.beresta.feature.onboarding.core.widget.OnboardingItem
import ru.maksonic.beresta.feature.onboarding.core.widget.OnboardingTopBar
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 24.04.2023
 */
private const val LAST_PAGE = 3
internal typealias SendMessage = (Msg) -> Unit

class OnboardingScreen : OnboardingApi.Ui {

    @Composable
    override fun Screen(router: OnboardingRouter) {
        OnboardingContainer(router = router)
    }
}

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
private fun OnboardingContainer(
    sandbox: OnboardingSandbox = koinViewModel(),
    router: OnboardingRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val currentSheetContent = rememberUpdatedState(model.value.currentSheetContent)
    val isLastCurrentPage = remember { derivedStateOf { pagerState.currentPage == LAST_PAGE } }

    HandleUiEffects(
        effects = sandbox.effects,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        modalBottomSheetState = model.value.modalBottomSheetState,
        router = router
    )

    ModalBottomSheetLayout(
        sheetState = model.value.modalBottomSheetState,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.Level0,
        scrimColor = scrim,
        sheetContent = {
            MultipleModalBottomSheetContent(
                send = sandbox::send,
                currentSheetContent = currentSheetContent,
                modalSheetState = model.value.modalBottomSheetState,
            )
        }
    ) {
        OnboardingsContent(
            onboardings = onboardings,
            send = sandbox::send,
            pagerState = pagerState,
            isLastCurrentPage = isLastCurrentPage.value,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun OnboardingsContent(
    onboardings: Array<OnboardingUi>,
    send: SendMessage,
    pagerState: PagerState,
    isLastCurrentPage: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingTopBar(
            showLanguageSelector = { send(Msg.Ui.OnShowLangPickerClicked) },
            showThemeSelector = { send(Msg.Ui.OnShowThemePickerClicked) },
        )

        OverscrollBehavior {
            HorizontalPager(
                pageCount = onboardings.count(),
                state = pagerState,
                modifier = modifier.weight(1f)
            ) { page ->

                val pagerProgress =
                    remember { derivedStateOf { pagerState.currentPageOffsetFraction > 0 } }
                val pageOffset = remember {
                    derivedStateOf {
                        pagerState.calculateCurrentOffsetForPage(page).absoluteValue
                    }
                }
                OnboardingItem(
                    item = onboardings[page],
                    image = images[page],
                    pageOffset = pageOffset,
                    pagerProgress = pagerProgress
                )
            }
        }
        NextSlideButton(send, isLastCurrentPage, modifier)
    }
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    pagerState: PagerState,
    modalBottomSheetState: ModalBottomSheetState,
    onGoogleAuthClicked: () -> Unit,
    router: OnboardingRouter
) {
    val scope = rememberCoroutineScope()
    val slideDuration = Theme.dimen.durationAnimOnboarding
    val pageWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val pageWidthPx = with(LocalDensity.current) { pageWidthDp.toPx() }

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.SlideNextPage -> {
                val slidePage = pageWidthPx + pagerState.currentPage

                scope.launch {
                    if (pagerState.currentPage != LAST_PAGE) {
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

            is Eff.NavigateToMain -> router.toMain()
            is Eff.HideModalSheet -> scope.launch { modalBottomSheetState.hide() }
            is Eff.ShowModalSheet -> scope.launch { modalBottomSheetState.show() }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    BerestaTheme {
        Box(Modifier.background(background)) {
            OnboardingsContent(
                onboardings = arrayOf(OnboardingUi.Preview),
                send = {},
                pagerState = rememberPagerState(),
                isLastCurrentPage = false,
            )
        }
    }
}