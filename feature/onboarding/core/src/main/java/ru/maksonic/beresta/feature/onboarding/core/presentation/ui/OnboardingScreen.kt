package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Eff
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.NextSlideButton
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.OnboardingItem
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.OnboardingTopBar
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.SelectLanguageBottomSheetDialogContent
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 15.02.2023
 */
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
    languageSheet: LanguageSelectorApi.Ui = get(),
    router: OnboardingRouter
) {
    val model = sandbox.model.collectAsState().value
    val send = sandbox::sendMsg
    val pagerState = rememberPagerState()
    val isLastCurrentPage = pagerState.currentPage == 3
    val languageSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    HandleUiEffects(
        effects = sandbox.effects,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        bottomSheetState = languageSheetState,
        router = router
    )

    ModalBottomSheetLayout(
        sheetState = languageSheetState,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.disable,
        sheetContent = {
            SelectLanguageBottomSheetDialogContent(
                send = send,
                languageSheet = languageSheet,
                languageSheetState = languageSheetState,
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
            val onboardingData = text.onboardingTextData.data

            OnboardingTopBar(showLanguageSelector = { send(Msg.Ui.OnSelectLanguageBtnClicked) })

            OverscrollBehavior {
                HorizontalPager(
                    count = model.onboardingImages.count(),
                    state = pagerState,
                    modifier = modifier.weight(1f)
                ) { page ->
                    OnboardingItem(
                        title = onboardingData[page].title,
                        description = onboardingData[page].description,
                        imageId = model.onboardingImages[page],
                        page = page, pagerScope = this)
                }
            }
            NextSlideButton(send, isLastCurrentPage , modifier)
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
            is Eff.HideLanguageSheet -> {
                scope.launch {
                    bottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
                }
            }

            is Eff.ShowLanguageSheet -> {
                scope.launch {
                    bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {

    BerestaTheme {
        Content(router = OnboardingRouter {})
    }
}