package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.ui.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.ui.Eff
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.feature.notes.api.ui.isBlank
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp0
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import ru.maksonic.beresta.ui.widget.toastLongTime

/**
 * @Author maksonic on 26.04.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class EditNoteExpandableScreen : EditNoteApi.Ui {

    @Composable
    override fun ExpandableScreen(
        router: EditNoteRouter?,
        isEntryPoint: Boolean,
        isHiddenNotes: Boolean,
        modifier: Modifier
    ) {
        ExpandableScreenContainer(
            isEntryPoint = isEntryPoint,
            router = router,
            isHiddenNotes = isHiddenNotes,
            modifier = modifier
        )
    }
}

@Composable
private fun ExpandableScreenContainer(
    isEntryPoint: Boolean,
    router: EditNoteRouter?,
    isHiddenNotes: Boolean,
    modifier: Modifier,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    HandleUiEffects(sandbox.effects, router, focusRequester)

    BackHandler(model.value.isExpandedFab) {
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
        val height = animateDp(if (model.value.isExpandedFab) fullHeight else fabSize, animSpeed)
        val width = animateDp(if (model.value.isExpandedFab) fullWidth else fabSize, animSpeed)
        val endPadding = animateDp(if (model.value.isExpandedFab) dp0 else dp16, animSpeed)
        val bottomPadding =
            animateDp(if (model.value.isExpandedFab) dp0 else initBottomPadding, animSpeed)
        val color = animateColorAsState(
            if (model.value.isExpandedFab) surface else tertiaryContainer, tween(animSpeed)
        )
        val isFullExpanded = height.value == fullHeight
        val fabShape = if (isFullExpanded) 0.dp else if (isHiddenNotes) 50.dp else dp16

        if (isEntryPoint) {
            EditNoteExpandedContent(model.value, sandbox::send, focusRequester)
        } else {
            SurfacePro(
                color = color.value,
                shape = RoundedCornerShape(fabShape),
                modifier = modifier
                    .padding(bottom = bottomPadding.value, end = endPadding.value)
                    .height(height.value)
                    .width(width.value),
            ) {
                AnimateContent(model.value.isExpandedFab, animSpeed = animSpeed) { isExpanded ->
                    if (isExpanded) {
                        EditNoteExpandedContent(
                            model = model.value,
                            send = sandbox::send,
                            focusRequester = focusRequester,
                            modifier = Modifier.size(width = width.value, height = height.value)
                        )
                    } else {
                        EditNoteCollapsedContent(
                            isBlankNote = model.value.currentNote.isBlank(),
                            onExpandFabClicked = { sandbox.send(Msg.Ui.OnExpandFabClicked) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    focusRequester: FocusRequester,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current
    val noteMaxLengthWarning = rememberUpdatedState(newValue = text.editNote.noteMaxLengthWarning)
    val keyboardDelay = Theme.animVelocity.common
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning.value)
            is Eff.ShowNoteUpdateSnackBar -> context.toastLongTime("Bye")
            is Eff.ShowKeyboardForExpandedFab -> {
                scope.launch {
                    delay(keyboardDelay.toLong())
                    focusRequester.requestFocus()
                    keyboard?.show()
                }
            }
        }
    }
}

