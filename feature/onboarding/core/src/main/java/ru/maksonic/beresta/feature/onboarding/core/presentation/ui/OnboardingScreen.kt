package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.core.R
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
    val model = sandbox.model.collectAsStateWithLifecycle()
    val send = sandbox::send
    val pagerState = rememberPagerState()
    val currentSheetContent = rememberUpdatedState(model.value.currentSheetContent)
    val modalBottomSheetState = rememberUpdatedState(model.value.modalBottomSheetState)
    val isLastCurrentPage = remember {
        derivedStateOf { pagerState.currentPage == LAST_ONBOARDING_PAGE }
    }

    HandleUiEffects(
        effects = sandbox.effects,
        pagerState = pagerState,
        onGoogleAuthClicked = {},
        bottomSheetState = modalBottomSheetState,
        router = router
    )

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState.value,
        sheetBackgroundColor = transparent,
        sheetContentColor = transparent,
        sheetElevation = Theme.elevation.Level0,
        scrimColor = scrim,
        sheetContent = {
            MultipleModalBottomSheetContent(
                send = send,
                currentSheetContent = currentSheetContent,
                modalSheetState = modalBottomSheetState,
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
            OnboardingTopBar(
                showLanguageSelector = { send(Msg.Ui.OnShowSelectLanguageSheetClicked) },
                showThemeSelector = { send(Msg.Ui.OnShowSelectThemeSheetClicked) },
            )

            OverscrollBehavior {
                HorizontalPager(
                    count = onboardings.count(),
                    state = pagerState,
                    modifier = modifier.weight(1f)
                ) { page ->
                    val pageOffset = remember {
                        derivedStateOf { this.calculateCurrentOffsetForPage(page).absoluteValue }
                    }
                    val pagerProgress = remember { derivedStateOf { this.currentPageOffset > 0 } }

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
}

private val onboardings: Array<OnboardingUi>
    @Composable get() = with(text.onboarding.data) {
        buildList {
            repeat(4) { index ->
                add(
                    OnboardingUi(
                        title = this@with[index].title, description = this@with[index].description
                    )
                )
            }
        }.toTypedArray()
    }

private val images = listOf(
    R.drawable.onboarding_image_first,
    R.drawable.onboarding_image_second,
    R.drawable.onboarding_image_third,
    R.drawable.onboarding_image_fourth
)

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    pagerState: PagerState,
    bottomSheetState: State<ModalBottomSheetState>,
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
                    bottomSheetState.value.hide()
                }
            }
            is Eff.ShowModalSheet -> {
                scope.launch {
                    bottomSheetState.value.show()
                }
            }
        }
    }
}