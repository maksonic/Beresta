package ru.maksonic.beresta.feature.onboarding.ui.core.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Language
import ru.maksonic.beresta.common.ui_kit.icons.ThemeSystem
import ru.maksonic.beresta.feature.onboarding.ui.core.Model
import ru.maksonic.beresta.feature.onboarding.ui.core.Msg
import ru.maksonic.beresta.feature.onboarding.ui.core.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.feature.onboarding.ui.core.widget.NextSlideButton
import ru.maksonic.beresta.feature.onboarding.ui.core.widget.OnboardingItem
import ru.maksonic.beresta.common.ui_kit.animation.OverscrollBehavior
import ru.maksonic.beresta.common.ui_kit.bar.top.TopAppBarLight
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.helpers.pager.calculateCurrentOffsetForPage
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import kotlin.math.absoluteValue

/**
 * @Author maksonic on 27.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    pagerState: PagerState,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier
) {
    val isLastCurrentPage = remember { derivedStateOf { pagerState.currentPage == LAST_PAGE } }

    Box {
        Column(modifier.systemBarsPadding()) {
            TopAppBarLight(
                navIcon = AppIcon.Language,
                navIconAction = { send(Msg.Ui.OnShowLangPickerClicked) },
                actions = {
                    ButtonIcon(
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
                        item = model.onboardings[page],
                        pageOffset = pageOffset,
                        pagerProgress = pagerProgress
                    )
                }
            }
            NextSlideButton(send, isLastCurrentPage, model.isFailFetched, modifier)
        }

        if (model.modalSheet.isVisible) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
            ) {
                MultipleModalBottomSheetContent(model.modalSheet.content)
            }
        }
    }
}