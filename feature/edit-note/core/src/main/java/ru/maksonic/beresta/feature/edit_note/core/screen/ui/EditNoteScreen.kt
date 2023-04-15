package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabUiSharedState
import ru.maksonic.beresta.feature.edit_note.api.collapseFab
import ru.maksonic.beresta.feature.edit_note.api.resetDraftFabIcon
import ru.maksonic.beresta.feature.edit_note.api.showDraftFabIcon
import ru.maksonic.beresta.feature.edit_note.core.screen.core.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.TopBarWithEditorPanelContainer
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel.EditorPanelState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.*
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.BottomSheetContainer
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.MultipleBottomSheetContent
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
import ru.maksonic.beresta.feature.notes_list.api.ui.isEmpty
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.isScrollUp
import ru.maksonic.beresta.ui.widget.toastLongTime

/**
 * @Author maksonic on 04.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    sharedFabUiState: SharedUiState<EditNoteFabUiSharedState> = EditNoteFabUiSharedState.Initial,
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val fabUiState = sharedFabUiState.state.collectAsState()
    val titleFocus = remember { FocusRequester() }
    val bottomSheetEditorState = rememberSaveable { mutableStateOf(BottomSheetEditor()) }
    val snackBarState = rememberSaveable { mutableStateOf(false) }

    HandleUiEffects(
        effects = sandbox.effects,
        router = router,
        titleFocus = titleFocus,
        bottomSheetEditorState = bottomSheetEditorState,
        sharedFabUiState = sharedFabUiState,
        snackBarState = snackBarState
    )

    LaunchedEffect(fabUiState.value.isExpandedFab) {
        sandbox.send(Msg.Inner.FetchedPassedFabStateResult(fabUiState.value.isExpandedFab))
    }

    Content(
        model = model,
        send = sandbox::send,
        sheetState = bottomSheetEditorState,
        titleFocus = titleFocus,
        fabUiState = fabUiState.value,
        snackBarState = snackBarState,
        modifier = modifier
    )
}

@Composable
fun Content(
    model: State<Model>,
    send: SendMessage,
    sheetState: State<BottomSheetEditor>,
    titleFocus: FocusRequester,
    fabUiState: EditNoteFabUiSharedState,
    snackBarState: MutableState<Boolean>,
    wallpaperSelectorApi: NoteWallpaperSelectorApi = get(),
    modifier: Modifier,
) {
    val scrollState = rememberLazyListState()
    val isScrollUp = scrollState.isScrollUp()
    val fetchedWallpaperResId = wallpaperSelectorApi.currentWallpaper.collectAsState()
    val maxLines = 500
    val isNewNote =
        model.value.isNewNote && sheetState.value.state != BottomSheetEditorState.EXPANDED
    val isShowKeyboard = isNewNote && fabUiState.isEndExpand

    LaunchedEffect(isShowKeyboard) {
        if (isShowKeyboard) {
            send(Msg.Inner.ShowKeyboardWithFocusOnTitle)
        }
    }

    LaunchedEffect(fetchedWallpaperResId.value) {
        send(Msg.Inner.UpdatedNoteWallpaper(fetchedWallpaperResId.value))
    }

    BackHandler(model.value.isNewNote) { send(Msg.Ui.OnTopBarBackPressed) }

    Box(
        modifier
            .fillMaxSize()
            .background(background)
    ) {
        val editorPanelState = rememberSaveable { mutableStateOf(EditorPanelState.IDLE) }

        ScreenBackground(model.value.currentNote.backgroundId)

        LazyColumn(
            state = scrollState,
            modifier = modifier
                .systemBarsPadding()
                .imePadding()
                .fillMaxSize()
        ) {

            item {
                Spacer(
                    modifier
                        .statusBarsPadding()
                        .height(Theme.widgetSize.topBarNormalHeight)
                )
            }

            item {
                NoteTitleInputFieldWidget(
                    title = model.value.currentNote.title,
                    updateTitle = { titleField -> send(Msg.Inner.UpdatedInputTitle(titleField)) },
                    focusRequester = titleFocus,
                    maxLines = maxLines,
                )
            }

            item {
                NoteMessageInputFieldWidget(
                    message = model.value.currentNote.message,
                    updateMessage = { msgField -> send(Msg.Inner.UpdatedInputMessage(msgField)) },
                    maxLines = maxLines,
                )
            }

            item {
                Spacer(
                    modifier
                        .statusBarsPadding()
                        .height(Theme.widgetSize.topBarNormalHeight)
                )
            }
        }

        TopBarWithEditorPanelContainer(
            send = send,
            isEmptyNote = model.value.currentNote.isEmpty(),
            editorPanelState = editorPanelState,
            onTopBarBackPressed = { send(Msg.Ui.OnTopBarBackPressed) },
            isVisibleEditorPanel = isScrollUp,
            isVisibleUpdatedNoteSnack = snackBarState,
            modifier = modifier
        )

        BottomSheetContainer(sheetState = sheetState.value.state) {

            MultipleBottomSheetContent(
                send = send,
                sheetState = sheetState.value.state,
                currentSheetContent = sheetState.value.currentContent,
                wallpaperSelector = wallpaperSelectorApi,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ScreenBackground(backgroundResId: Int, modifier: Modifier = Modifier) {
    val noteBackground = if (backgroundResId == 0) R.drawable.color_transparent else backgroundResId

    AsyncImage(
        model = noteBackground,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    titleFocus: FocusRequester,
    bottomSheetEditorState: MutableState<BottomSheetEditor>,
    sharedFabUiState: SharedUiState<EditNoteFabUiSharedState>,
    snackBarState: MutableState<Boolean>,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val noteMaxLengthWarning = text.editNote.noteMaxLengthWarning
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->

        when (eff) {
            is Eff.ShowKeyboardWithTitleFocus -> {
                scope.launch {
                    titleFocus.requestFocus().let {
                        keyboard?.hide()
                        delay(300L)
                        keyboard?.show()
                    }
                }
            }
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning)
            is Eff.HideSystemKeyboard -> focusManager.clearFocus().let { keyboard?.hide() }
            is Eff.CollapseFab -> focusManager.clearFocus().let { sharedFabUiState.collapseFab() }
            is Eff.ShowFabDraftIcon -> sharedFabUiState.showDraftFabIcon()
            is Eff.ResetFabDraftIcon -> sharedFabUiState.resetDraftFabIcon()
            is Eff.SetNothingSheetContent -> bottomSheetEditorState.clearContent()
            is Eff.SetWallpaperSheetContent -> bottomSheetEditorState.setWallpaperContent()
            is Eff.CollapseBottomSheet -> bottomSheetEditorState.collapse()
            is Eff.ExpandBottomSheet -> bottomSheetEditorState.expand()
            is Eff.ShowNoteUpdateSnackBar -> {
                scope.launch {
                    snackBarState.value = true
                    delay(2000L)
                    snackBarState.value = false
                }
            }
        }
    }
}