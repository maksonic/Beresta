package ru.maksonic.beresta.feature.edit_note.core.fab.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabUiSharedState
import ru.maksonic.beresta.feature.edit_note.api.collapseFab
import ru.maksonic.beresta.feature.edit_note.api.expandFab
import ru.maksonic.beresta.feature.edit_note.core.fab.core.AddNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.fab.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.fab.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.EditNoteScreen
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp0
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.DriveFile
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.widget.button.FloatingFabButton
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 23.02.2023
 */
internal typealias FabSendMessage = (Msg) -> Unit

private const val DURATION = 450

class AddNoteFabWidget : EditNoteApi.Ui {
    companion object {
        private const val FAB_VISIBILITY_DURATION = 400
    }

    override val sharedUiState = EditNoteFabUiSharedState.Initial

    @Composable
    override fun NewNoteFabWidget(
        isVisible: Boolean,
        isNotesScrollUp: State<Boolean>,
        modifier: Modifier
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideIn(
                animationSpec = tween(FAB_VISIBILITY_DURATION),
                initialOffset = { IntOffset(0, 300) }) + fadeIn(tween(FAB_VISIBILITY_DURATION)),
            exit = slideOut(
                animationSpec = tween(FAB_VISIBILITY_DURATION),
                targetOffset = { IntOffset(0, 300) }) + fadeOut(tween(FAB_VISIBILITY_DURATION))
        ) {
            Content(isScrollUp = isNotesScrollUp)
        }
    }

    @Composable
    private fun Content(
        isScrollUp: State<Boolean>,
        sandbox: AddNoteSandbox = koinViewModel()
    ) {
        val sharedFabState = sharedUiState.state.collectAsState().value
        val isExpandedFab = rememberSaveable { mutableStateOf(false) }

        LaunchedEffect(sharedFabState.isExpandedFab) {
            isExpandedFab.value = sharedFabState.isExpandedFab
        }

        HandleUiEffects(sandbox.effects, sharedUiState, isExpandedFab)

        BackHandler(isExpandedFab.value) {
            if (isExpandedFab.value) {
                sandbox.send(Msg.Ui.OnCollapseFabClicked)
            }
        }

        FabContainer(
            send = sandbox::send,
            isExpanded = isExpandedFab,
            isScrollUp = isScrollUp,
        )
    }

    @Composable
    private fun FabContainer(
        send: FabSendMessage,
        isExpanded: State<Boolean>,
        isScrollUp: State<Boolean>,
        modifier: Modifier = Modifier,
    ) {
        val sharedFabUiState = sharedUiState.state.collectAsState().value

        BoxWithConstraints(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            val fullHeight = this.maxHeight
            val fullWidth = this.maxWidth
            val fabSize = Theme.widgetSize.btnFabSize
            val navBarHeight =
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            val fabColor = animateColorAsState(
                if (isExpanded.value) surface else tertiaryContainer,
                animationSpec = tween(DURATION)
            )
            val containerElevation = animateDpAsState(
                if (!isScrollUp.value) Theme.elevation.Level3 else Theme.elevation.Level0
            )
            val containerHeight = animateDpAsState(
                if (isExpanded.value) fullHeight else fabSize, animationSpec = tween(DURATION)
            )
            val containerWidth = animateDpAsState(
                if (isExpanded.value) fullWidth else fabSize, animationSpec = tween(DURATION)
            )
            val containerBottomPadding = animateDpAsState(
                if (isExpanded.value) dp0 else navBarHeight.plus(dp12), tween(DURATION)
            )
            val containerEndPadding =
                animateDpAsState(if (isExpanded.value) dp0 else dp16, tween(DURATION))
            val expandedContentAlpha =
                animateFloatAsState(if (isExpanded.value) 1f else 0f, tween(DURATION))
            val collapsedContentAlpha =
                animateFloatAsState(if (isExpanded.value) 0f else 1f, tween(DURATION))
            val isFullExpanded = containerHeight.value == fullHeight
            val containerShape = if (isFullExpanded) 0.dp else dp16

            LaunchedEffect(isFullExpanded) {
                sharedUiState.updateState { it.copy(isEndExpand = isFullExpanded) }
            }

            FloatingFabButton(
                onClick = { },
                fabColor = surface,
                enabled = false,
                shape = RoundedCornerShape(containerShape),
                shadowElevation = containerElevation.value,
                modifier = modifier
                    .padding(
                        bottom = containerBottomPadding.value,
                        end = containerEndPadding.value
                    )
                    .height(containerHeight.value)
                    .width(containerWidth.value),
            ) {
                Box {
                    if (!isExpanded.value) {
                        Box(
                            modifier
                                .fillMaxSize()
                                .drawBehind { drawRect(fabColor.value) }
                                .clickAction(surface) { send(Msg.Ui.OnCreateNewNoteClicked) },
                            contentAlignment = Alignment.Center
                        ) {
                            val icon = if (sharedFabUiState.isVisibleOnFabDraftIndicator)
                                AppIcon.DriveFile
                            else AppIcon.Edit

                            Icon(
                                imageVector = icon,
                                tint = onTertiaryContainer,
                                contentDescription = "",
                                modifier = modifier.graphicsLayer {
                                    alpha = collapsedContentAlpha.value
                                }
                            )
                        }
                    } else {
                        EditNoteScreen(
                            sharedFabUiState = sharedUiState,
                            modifier = modifier.graphicsLayer {
                                alpha = expandedContentAlpha.value
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    sharedUiState: SharedUiState<EditNoteFabUiSharedState>,
    isExpandedFab: MutableState<Boolean>
) {
    HandleEffectsWithLifecycle(effects) { eff ->

        when (eff) {
            Eff.SetExpandFabSharedState -> {
                sharedUiState.expandFab().let { isExpandedFab.value = true }
            }

            Eff.SetCollapseFabSharedState -> {
                sharedUiState.collapseFab().let { isExpandedFab.value = false }
            }
        }
    }
}