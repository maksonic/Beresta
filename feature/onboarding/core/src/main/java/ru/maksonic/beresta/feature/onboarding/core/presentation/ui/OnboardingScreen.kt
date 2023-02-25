package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Eff
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.NextSlideButton
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.OnboardingItem
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.OnboardingTopBar
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 15.02.2023
 */
private const val LAST_ONBOARDING_PAGE = 3
internal typealias SendMessage = (Msg) -> Unit

class OnboardingScreen : OnboardingApi.Ui {

    @Composable
    override fun Screen(router: OnboardingRouter) {
        Content(router = router)
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    sandbox: OnboardingSandbox = koinViewModel(),
    router: OnboardingRouter
) {
    val model = sandbox.model.collectAsState().value
    val send = sandbox::sendMsg
    val pagerState = rememberPagerState()
    val isLastCurrentPage = pagerState.currentPage == LAST_ONBOARDING_PAGE

    HandleUiEffects(
        effects = sandbox.effects,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        bottomSheetState = model.modalBottomSheetState,
        router = router
    )

    ModalBottomSheetLayout(
        sheetState = model.modalBottomSheetState,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.disable,
        scrimColor = scrim,
        sheetContent = {
            MultipleModalBottomSheetContent(
                send = send,
                currentSheetContent = model.currentSheetContent,
                modalSheetState = { model.modalBottomSheetState },
                modifier = modifier
            )
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Fetch text from local provider for build onboardings.
            send(Msg.Inner.FetchOnboardingTextData(text.onboarding.data))

            OnboardingTopBar(
                showLanguageSelector = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) },
                showThemeSelector = { send(Msg.Ui.OnShowSelectThemeSheetClicked) },
            )

            OverscrollBehavior {
                HorizontalPager(
                    count = model.onboardings.count(),
                    state = pagerState,
                    modifier = modifier.weight(1f)
                ) { page ->
                    val pageOffset = this.calculateCurrentOffsetForPage(page).absoluteValue
                    val pagerProgress = this.currentPageOffset > 0

                    OnboardingItem(
                        item = model.onboardings[page],
                        pageOffset = { pageOffset },
                        pagerProgress = { pagerProgress }
                    )
                }
            }
            NextSlideButton(send, isLastCurrentPage, modifier)
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    pagerState: PagerState,
    bottomSheetState: ModalBottomSheetState,
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
                val slidePage = pageWidthPx + pagerState.pageCount

                scope.launch {
                    if (pagerState.currentPage != pagerState.pageCount - 1) {
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
            is Eff.HideModalSheet -> {
                scope.launch {
                    bottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                }
            }
            is Eff.ShowModalSheet -> {
                scope.launch {
                    bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    }
}