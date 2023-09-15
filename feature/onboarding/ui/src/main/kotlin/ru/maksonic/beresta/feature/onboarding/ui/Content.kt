package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.ui.core.Msg
import ru.maksonic.beresta.feature.onboarding.ui.core.OnboardingUi
import ru.maksonic.beresta.feature.onboarding.ui.widget.NextSlideButton
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.theme.icons.ThemeSystem
import ru.maksonic.beresta.ui.widget.bar.top.TopAppBarDefault
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.OverscrollBehavior
import ru.maksonic.beresta.ui.widget.pager.calculateCurrentOffsetForPage
import ru.maksonic.beresta.ui.widget.sheet.ModalBottomSheetDefault
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 19.06.2023
 */
private const val PAGES_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    send: SendMessage,
    pagerState: PagerState,
    bottomSheetState: SheetState,
    currentSheetContent: OnboardingApi.Ui.BottomSheetContent,
    isVisibleModalSheet: Boolean,
    modifier: Modifier = Modifier
) {
    val isLastCurrentPage = remember { derivedStateOf { pagerState.currentPage == LAST_PAGE } }

    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column {
            TopAppBarDefault(
                navIcon = AppIcon.Language,
                navIconAction = { send(Msg.Ui.OnShowLangPickerClicked) },
                actions = {
                    ClickableIcon(
                        icon = AppIcon.ThemeSystem,
                        onClick = { send(Msg.Ui.OnShowThemePickerClicked) })
                }
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

        if (isVisibleModalSheet) {
            ModalBottomSheetDefault(
                sheetState = bottomSheetState,
                onDismissRequest = { send(Msg.Inner.UpdatedModalSheetVisibility(false)) },
            ) {
                MultipleModalBottomSheetContent(
                    send = send,
                    currentSheetContent = currentSheetContent
                )
            }
        }
    }
}

internal val onboardings: Array<OnboardingUi>
    @Composable get() = with(text.onboarding.data) {
        buildList {
            repeat(PAGES_COUNT) { index ->
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