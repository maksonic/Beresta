package ru.maksonic.beresta.feature.ui.edit_note.core.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_kit.toastLongTime
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.surface
import ru.maksonic.beresta.common.ui_theme.provide.dp0
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.isBlank
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.isExpanded
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.ui.edit_note.core.Eff
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.routes.EditNoteRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 16.10.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Container(
    router: EditNoteRouter?,
    state: EditNoteFabState,
    addFolderDialogApi: AddFolderDialogUiApi = koinInject(),
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore = koinInject(),
    updateFabState: (EditNoteFabState) -> Unit,
    isEntryPoint: Boolean,
    isCircleFab: Boolean,
    modifier: Modifier,
    sandbox: EditNoteSandbox = koinViewModel(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val isExpanded = rememberUpdatedState(state.isExpanded)
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        addChipDialogApi = addFolderDialogApi,
        focusRequester = focusRequester,
        modalBottomSheetState = modalBottomSheetState,
        collapseFab = updateFabState,
        hideModalSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) }
    )

    BackHandler(isExpanded.value) {
        sandbox.send(Msg.Ui.OnTopBarBackPressed)
    }

    BoxWithConstraints(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        val animSpeed = Theme.animVelocity.createNoteFabExpand
        val fullHeight = this.maxHeight
        val fullWidth = this.maxWidth
        val fabSize = Theme.size.btnFabSize
        val initBottomPadding = SystemNavigationBarHeight.plus(dp12)
        val height = animateDpAsState(
            if (isExpanded.value) fullHeight else fabSize, tween(animSpeed), label = ""
        )
        val width = animateDpAsState(
            if (isExpanded.value) fullWidth else fabSize, tween(animSpeed), label = ""
        )
        val endPadding = animateDpAsState(
            if (isExpanded.value) dp0 else dp16, tween(animSpeed), label = ""
        )
        val bottomPadding = animateDpAsState(
            if (isExpanded.value) dp0 else initBottomPadding, tween(animSpeed), label = ""
        )
        val isFullExpanded = height.value == fullHeight
        val fabShape = if (isFullExpanded) 0.dp else if (isCircleFab) 50.dp else dp16

        if (isEntryPoint) {
            LaunchedEffect(Unit) {
                sandbox.send(Msg.Inner.UpdatedEntryPointValue(true))
            }

            ContentExpanded(
                model = model,
                send = sandbox::send,
                currentFolderStoreUiApi = currentFolderStoreUiApi,
                focusRequester = focusRequester,
                modalBottomSheetState = modalBottomSheetState,
                isHiddenNote = model.isHiddenNote,
                addFolderDialogApi = addFolderDialogApi
            )
        } else {

            LaunchedEffect(Unit) {
                sandbox.send(Msg.Inner.UpdatedEntryPointValue(false))
            }

            LaunchedEffect(isFullExpanded) {
                if (isFullExpanded && !model.markerState.isVisibleDialog) {
                    sandbox.send(Msg.Inner.ShowedKeyboardForExpandedFab(false))
                }
            }

            SurfacePro(
                color = surface,
                shape = RoundedCornerShape(fabShape),
                modifier = modifier
                    .padding(bottom = bottomPadding.value, end = endPadding.value)
                    .size(width.value, height.value)
            ) {

                if (isExpanded.value) {
                    ContentExpanded(
                        model = model,
                        send = sandbox::send,
                        addFolderDialogApi = addFolderDialogApi,
                        currentFolderStoreUiApi = currentFolderStoreUiApi,
                        focusRequester = focusRequester,
                        isHiddenNote = model.isHiddenNote || isCircleFab,
                        modalBottomSheetState = modalBottomSheetState,
                    )
                }

                AnimateFadeInOut(
                    !state.isExpanded,
                    fadeInDuration = animSpeed,
                    fadeOutDuration = animSpeed
                ) {
                    ContentCollapsed(
                        isBlankNote = model.editableNote.isBlank(),
                        onExpandFabClicked = { updateFabState(EditNoteFabState.EXPANDED) },
                        modifier = modifier.alpha(if (state.isExpanded) 0f else 1f)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    addChipDialogApi: AddFolderDialogUiApi,
    focusRequester: FocusRequester,
    modalBottomSheetState: SheetState,
    collapseFab: (EditNoteFabState) -> Unit,
    hideModalSheet: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val noteMaxLengthWarning = rememberUpdatedState(newValue = text.editor.noteMaxLengthWarning)

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning.value)
            is Eff.ShowNoteUpdateSnackBar -> context.toastLongTime("Bye")
            is Eff.ShowKeyboardForExpandedFab -> focusRequester.requestFocus()

            is Eff.HideKeyboard -> focusManager.clearFocus()
            is Eff.ShowAddNewChipDialog -> {
                addChipDialogApi.addFolder()
            }

            is Eff.CollapseFab -> focusManager.clearFocus().run {
                scope.launch {
                    delay(100)
                    collapseFab(EditNoteFabState.COLLAPSED)
                    this.coroutineContext.cancel()
                }
            }

            is Eff.UpdateCurrentFolder -> {/*foldersListApi.updateCurrent(eff.id)*/
            }

            is Eff.ShowMarkerColorPickerDialog -> {
                /*markerColorPickerApi.showWithColor(eff.colorId)*/
            }

            is Eff.ShowModalSheet -> scope.launch { modalBottomSheetState.show() }
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideModalSheet()
                    }
                }
            }
        }
    }
}
