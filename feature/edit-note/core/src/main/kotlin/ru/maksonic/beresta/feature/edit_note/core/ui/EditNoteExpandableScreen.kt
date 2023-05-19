package ru.maksonic.beresta.feature.edit_note.core.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.Msg
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.isBlank
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp0
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.button.FloatingFabButton
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
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
        fabModifier: Modifier
    ) {

        ExpandableScreenContainer(
            isEntryPoint = isEntryPoint,
            router = router,
            modifier = fabModifier
        )
    }
}

@Composable
private fun ExpandableScreenContainer(
    sandbox: EditNoteSandbox = koinViewModel(),
    editNoteApi: NotesListApi.Ui = get(),
    isEntryPoint: Boolean,
    router: EditNoteRouter?,
    modifier: Modifier
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val sharedNoteListUiState = editNoteApi.sharedUiState.state.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isEntryPoint) {
        sandbox.send(Msg.Inner.CheckedEntryPoint(isEntryPoint))
    }

    BackHandler(model.value.isExpandedFab) {
        sandbox.send(Msg.Ui.OnTopBarBackPressed)
    }

    HandleUiEffects(sandbox.effects, router, focusRequester)

    BoxWithConstraints(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        val fabAnimSpeed = Theme.animSpeed.createNoteFabExpand
        val fullHeight = this.maxHeight
        val fullWidth = this.maxWidth
        val fabSize = Theme.widgetSize.btnFabSize
        val fabHeight = animateDpAsState(
            if (model.value.isExpandedFab) fullHeight else fabSize, tween(fabAnimSpeed), label = ""
        )
        val fabWidth = animateDpAsState(
            if (model.value.isExpandedFab) fullWidth else fabSize, tween(fabAnimSpeed), label = ""
        )
        val fabEndPadding =
            animateDpAsState(
                if (model.value.isExpandedFab) dp0 else dp16,
                tween(fabAnimSpeed),
                label = ""
            )
        val fabBottomPadding = animateDpAsState(
            if (model.value.isExpandedFab) dp0 else SystemNavigationBarHeight.plus(dp12),
            tween(fabAnimSpeed), label = ""
        )
        val fabColor = animateColorAsState(
            if (model.value.isExpandedFab) surface else tertiaryContainer,
            animationSpec = tween(fabAnimSpeed), label = ""
        )
        val isFullExpanded = fabHeight.value == fullHeight
        val fabShape = if (isFullExpanded) 0.dp else dp16
        val isScrollUpElevation = if (sharedNoteListUiState.value.isScrollUp) Theme.elevation.Level0
        else Theme.elevation.Level3

        val fabElevation = animateDpAsState(
            if (sharedNoteListUiState.value.isSelectionState) Theme.elevation.Level0
            else isScrollUpElevation, label = ""
        )
        val collapsedContentAlpha = animateFloatAsState(
            if (model.value.isExpandedFab) 0f else 1f, tween(fabAnimSpeed), label = ""
        )
        val expandedContentAlpha = animateFloatAsState(
            if (model.value.isExpandedFab) 1f else 0f, tween(fabAnimSpeed), label = ""
        )

        if (isEntryPoint) {
            EditNoteExpandedContent(model.value, sandbox::send, focusRequester)
        } else {
            FloatingFabButton(
                fabColor = fabColor.value,
                enabled = false,
                shape = RoundedCornerShape(fabShape),
                shadowElevation = fabElevation.value,
                modifier = modifier
                    .padding(bottom = fabBottomPadding.value, end = fabEndPadding.value)
                    .height(fabHeight.value)
                    .width(fabWidth.value),
            ) {
                if (model.value.isExpandedFab) {
                    EditNoteExpandedContent(
                        model = model.value,
                        send = sandbox::send,
                        focusRequester = focusRequester,
                        modifier = Modifier.alpha(expandedContentAlpha.value)
                    )
                } else {
                    EditNoteCollapsedContent(
                        isBlankNote = model.value.currentNote.isBlank(),
                        contentAlpha = collapsedContentAlpha,
                        onExpandFabClicked = { sandbox.send(Msg.Ui.OnExpandFabClicked) }
                    )
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

    HandleEffectsWithLifecycle(effects) { eff ->

        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning.value)
            is Eff.ShowNoteUpdateSnackBar -> context.toastLongTime("Bye")
            is Eff.ShowKeyboardForExpandedFab -> {
                scope.launch {
                    focusRequester.requestFocus().let {
                        keyboard?.hide()
                        delay(300L)
                        keyboard?.show()
                    }
                }
            }
        }
    }
}

