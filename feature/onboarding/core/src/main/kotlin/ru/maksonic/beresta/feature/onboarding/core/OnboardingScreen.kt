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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import ru.maksonic.beresta.feature.onboarding.core.widget.ModalSheetContent
import ru.maksonic.beresta.feature.onboarding.core.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.feature.onboarding.core.widget.NextSlideButton
import ru.maksonic.beresta.feature.onboarding.core.widget.OnboardingItem
import ru.maksonic.beresta.feature.onboarding.core.widget.OnboardingTopBar
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.pager.calculateCurrentOffsetForPage
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault
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
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class
)
@Composable
private fun OnboardingContainer(
    sandbox: OnboardingSandbox = koinViewModel(),
    router: OnboardingRouter
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val pagesCount = onboardings.count()
    val pagerState = rememberPagerState { pagesCount }
    val currentSheetContent = rememberUpdatedState(model.value.currentSheetContent)
    val isLastCurrentPage = remember { derivedStateOf { pagerState.currentPage == LAST_PAGE } }

    HandleUiEffects(
        effects = sandbox.effects,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        modalBottomSheetState = model.value.modalBottomSheetState,
        router = router
    )

    OnboardingsContent(
        model = model.value,
        currentSheetContent = currentSheetContent,
        onboardings = onboardings,
        send = sandbox::send,
        pagerState = pagerState,
        isLastCurrentPage = isLastCurrentPage.value,
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun OnboardingsContent(
    model: Model,
    currentSheetContent: State<ModalSheetContent>,
    onboardings: Array<OnboardingUi>,
    send: SendMessage,
    pagerState: PagerState,
    isLastCurrentPage: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OnboardingTopBar(
                showLanguageSelector = { send(Msg.Ui.OnShowLangPickerClicked) },
                showThemeSelector = { send(Msg.Ui.OnShowThemePickerClicked) },
            )

            OverscrollBehavior {
                HorizontalPager(
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

        if (model.isVisibleModalSheet) {
            ModalBottomSheetDefault(
                sheetState = model.modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.UpdatedModalSheetState(false)) },
            ) {
                MultipleModalBottomSheetContent(send, currentSheetContent)
            }
        }
    }
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    pagerState: PagerState,
    modalBottomSheetState: SheetState,
    onGoogleAuthClicked: () -> Unit,
    hideSheet: () -> Unit,
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
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    val model = Model.Initial
    val currentSheetContent = rememberUpdatedState(model.currentSheetContent)

    BerestaTheme {
        Box(Modifier.background(background)) {
            OnboardingsContent(
                model = model,
                currentSheetContent = currentSheetContent,
                onboardings = arrayOf(OnboardingUi.Preview),
                send = {},
                pagerState = rememberPagerState { 1 },
                isLastCurrentPage = false,
            )
        }
    }
}