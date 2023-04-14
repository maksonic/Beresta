package ru.maksonic.beresta.screen.main.presentation.ui.widget.bottom_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.selected_items_counter_panel.api.SelectedItemsPanelUiApi
import ru.maksonic.beresta.screen.main.presentation.core.Msg
import ru.maksonic.beresta.screen.main.presentation.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.functional.ANIMATION_DURATION_NORMAL

/**
 * @Author maksonic on 24.03.2023
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainBottomBar(
    state: MainBottomBarState,
    send: SendMessage,
    isScrollUp: () -> Boolean,
    isSelectionState: () -> Boolean,
    selectedCount: () -> Int,
    removedCount: () -> Int,
    isShowUnpinItem: Boolean,
    isVisibleUndoRemoveNotesSnack: Boolean,
    panelCounterApi: SelectedItemsPanelUiApi,
    modifier: Modifier = Modifier
) {
    val navBarHeight = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val panelHeight = Theme.widgetSize.bottomMainPanelHeight.plus(navBarHeight)
    val panelOffset = animateDpAsState(
        targetValue = if (isSelectionState()) 0.dp else {
            if (isScrollUp()) 0.dp else panelHeight
        },
        animationSpec = tween(ANIMATION_DURATION_NORMAL)
    )

    Column {
        AnimatedVisibility(isVisibleUndoRemoveNotesSnack) {
            SnackBarAction(
                message = text.shared.hintRemovedNotesCount.plus(" ${removedCount()}"),
                actionTitle = text.shared.btnTitleCancel,
                onClick = {
                    send(Msg.Ui.OnRemoveNotesUndoClicked)
                    send(Msg.Ui.OnCancelSelectionClicked)
                }
            )
        }

        Box(
            modifier
                .fillMaxWidth()
                .graphicsLayer {
                    translationY = panelOffset.value.toPx()
                }
        ) {
            SurfacePro(
                tonalElevation = Theme.tonal.Level4,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(it)
                ) {
                    AnimatedContent(targetState = state) { barState ->
                        when (barState) {
                            MainBottomBarState.IDLE -> {
                                BottomBarIdleContent(send)
                            }

                            MainBottomBarState.SELECTION -> {
                                BottomBarSelectionContent(
                                    send = send,
                                    selectedCount = selectedCount,
                                    isShowUnpin = isShowUnpinItem,
                                    panelCounterApi = panelCounterApi
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

