package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.onboarding.domain.OnboardingEntity
import ru.maksonic.beresta.feature.onboarding.ui.core.Feature
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingSandbox
import ru.maksonic.beresta.feature.onboarding.ui.widget.OnboardingItem
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.component.dimenAnimFast
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton
import ru.maksonic.beresta.ui.widget.button.TertiaryButton
import ru.maksonic.beresta.ui.widget.functional.OverscrollBehavior

/**
 * @Author maksonic on 15.12.2022
 */

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    val mock = OnboardingEntity.preview()
        .copy(image = ru.maksonic.beresta.ui.theme.R.drawable.maksonic_logo)

    BerestaTheme {
        OnboardingScreenContent(
            onboardings = arrayOf(mock),
            pagerState = rememberPagerState(),
            sendMsg = {}
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(sandbox: OnboardingSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsState().value
    val pagerState = rememberPagerState()

    HandleUiEffects(sandbox.effects, pagerState) {}

    OnboardingScreenContent(
        onboardings = model.onboardings,
        pagerState = pagerState,
        sendMsg = sandbox::sendMsg
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnboardingScreenContent(
    onboardings: Array<OnboardingEntity>,
    pagerState: PagerState,
    sendMsg: (Feature.Msg) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLastCurrentPage = pagerState.currentPage == onboardings.lastIndex
    val titlePrimaryBtn = if (isLastCurrentPage) R.string.btnTitleAuth else R.string.btnTitleNext
    val alphaDoNotSyncBtn: Float by animateFloatAsState(
        targetValue = if (isLastCurrentPage) 1f else 0f,
        animationSpec = tween(durationMillis = dimenAnimFast, easing = LinearEasing)
    )

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OverscrollBehavior {
            HorizontalPager(
                count = onboardings.count(),
                state = pagerState,
                modifier = modifier.weight(1f)
            ) { page ->
                OnboardingItem(currentItem = onboardings[page], page = page, pagerScope = this)
            }
        }
        PrimaryButton(
            action = { sendMsg(Feature.Msg.Ui.OnPrimaryBtnClicked) },
            title = stringResource(id = titlePrimaryBtn),
        )
        Spacer(modifier = modifier.padding(bottom = dp16))
        TertiaryButton(
            action = { sendMsg(Feature.Msg.Ui.OnSkipSyncBtnClicked) },
            title = stringResource(R.string.btnSkipAuthTitle),
            modifier = modifier.alpha(alphaDoNotSyncBtn)
        )
        Spacer(modifier = modifier.padding(bottom = dp16))
    }
}
