package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import ru.maksonic.beresta.feature.language_selector.api.text
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Eff
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.onboarding.core.presentation.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget.OnboardingItem
import ru.maksonic.beresta.navigation.router.router.OnboardingRouter
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.dimenAnimFast
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.button.TertiaryButton
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior

/**
 * @Author maksonic on 15.02.2023
 */
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
    val pagerState = rememberPagerState()
    val languageSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val isLastCurrentPage = pagerState.currentPage == model.onboardings.lastIndex
    val titlePrimaryBtn = if (isLastCurrentPage) text.onboarding.onboardingSyncBtnTitle
    else text.onboarding.onboardingNextBtnTitle
    val alphaDoNotSyncBtn: Float by animateFloatAsState(
        targetValue = if (isLastCurrentPage) 1f else 0f,
        animationSpec = tween(durationMillis = dimenAnimFast, easing = LinearEasing)
    )

    HandleUiEffects(sandbox.effects, pagerState, onGoogleAuthClicked = {}, router)

    ModalBottomSheetLayout(
        sheetState = languageSheetState,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.disable,
        sheetContent = {
            Box(
                modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 1.dp)
            ) {
                languageSheet.BottomSheet(state = { languageSheetState }, modifier)
            }
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(Theme.widgetSize.topBarNormalHeight)
                    .padding(end = dp4),
                contentAlignment = Alignment.CenterEnd
            ) {
                IconAction(icon = { AppIcon.Language }) {
                    scope.launch {
                        languageSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    }
                }
            }
            OverscrollBehavior {
                HorizontalPager(
                    count = model.onboardings.count(),
                    state = pagerState,
                    modifier = modifier.weight(1f)
                ) { page ->
                    OnboardingItem(item = model.onboardings[page], page = page, pagerScope = this)
                }
            }

            PrimaryButton(
                action = { sandbox.sendMsg(Msg.Ui.OnPrimaryBtnClicked) },
                title = titlePrimaryBtn,
            )

            Spacer(modifier.height(dp8))

            TertiaryButton(
                action = { sandbox.sendMsg(Msg.Ui.OnSkipSyncBtnClicked) },
                title = "text.onboarding.onboardingNotSyncBtnTitle",
                modifier = modifier.alpha(alphaDoNotSyncBtn)
            )
            Spacer(modifier = modifier.padding(bottom = dp16))
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
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