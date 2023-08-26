package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.edit_note.ui.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.ui.Eff
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.feature.notes.api.ui.isBlank
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.component.dp0
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import ru.maksonic.beresta.ui.widget.toastLongTime

/**
 * @Author maksonic on 29.07.2023
 */
@Composable
internal fun Container(
    router: EditNoteRouter?,
    state: State<EditNoteFabState>,
    updateFabState: (EditNoteFabState) -> Unit,
    isEntryPoint: Boolean,
    isCircleFab: Boolean,
    modifier: Modifier,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val isExpanded = rememberUpdatedState(state.value.isExpanded)
    val isHiddenNotes = rememberUpdatedState(model.value.isHiddenNote)

    HandleUiEffects(sandbox.effects, router, focusRequester, collapseFab = updateFabState)

    BackHandler(isExpanded.value) {
        sandbox.send(Msg.Ui.OnTopBarBackPressed)
    }

    LaunchedEffect(isEntryPoint) {
        sandbox.send(Msg.Inner.CheckedEntryPoint(isEntryPoint))
    }

    BoxWithConstraints(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        val animSpeed = Theme.animVelocity.createNoteFabExpand
        val fullHeight = this.maxHeight
        val fullWidth = this.maxWidth
        val fabSize = Theme.widgetSize.btnFabSize
        val initBottomPadding = SystemNavigationBarHeight.plus(dp12)
        val height = animateDp(if (isExpanded.value) fullHeight else fabSize, animSpeed)
        val width = animateDp(if (isExpanded.value) fullWidth else fabSize, animSpeed)
        val endPadding = animateDp(if (isExpanded.value) dp0 else dp16, animSpeed)
        val bottomPadding = animateDp(if (isExpanded.value) dp0 else initBottomPadding, animSpeed)
        val isFullExpanded = height.value == fullHeight
        val fabShape = if (isFullExpanded) 0.dp else if (isCircleFab) 50.dp else dp16

        if (isEntryPoint) {
            ExpandedContent(model.value, sandbox::send, focusRequester, isHiddenNotes.value)
        } else {

            LaunchedEffect(isFullExpanded) {
                if (isFullExpanded) {
                    sandbox.send(Msg.Inner.ShowedKeyboardForExpandedFab)
                }
            }

            SurfacePro(
                color = surface,
                shape = RoundedCornerShape(fabShape),
                modifier = modifier
                    .padding(bottom = bottomPadding.value, end = endPadding.value)
                    .height(height.value)
                    .width(width.value)
                    .animateContentSize(tween(animSpeed)),
            ) {

                if (state.value.isExpanded) {
                    ExpandedContent(
                        model = model.value,
                        send = sandbox::send,
                        focusRequester = focusRequester,
                        isHiddenNote = isHiddenNotes.value || isCircleFab,
                        modifier = Modifier.size(width = width.value, height = height.value)
                    )
                }

                AnimateFadeInOut(
                    !state.value.isExpanded,
                    fadeInDuration = animSpeed,
                    fadeOutDuration = animSpeed
                ) {
                    val alpha = if (state.value.isExpanded) 0f else 1f

                    CollapsedContent(
                        isBlankNote = model.value.currentNote.isBlank(),
                        onExpandFabClicked = { updateFabState(EditNoteFabState.EXPANDED) },
                        modifier = modifier.alpha(alpha)
                    )
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    focusRequester: FocusRequester,
    collapseFab: (EditNoteFabState) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val noteMaxLengthWarning = rememberUpdatedState(newValue = text.editNote.noteMaxLengthWarning)

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning.value)
            is Eff.ShowNoteUpdateSnackBar -> context.toastLongTime("Bye")
            is Eff.ShowKeyboardForExpandedFab ->  focusRequester.requestFocus()
            is Eff.CollapseFab -> focusManager.clearFocus().run {
                scope.launch {
                    delay(100)
                    collapseFab(EditNoteFabState.COLLAPSED)
                }
            }
        }
    }
}