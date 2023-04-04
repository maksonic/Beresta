package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
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
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Model
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.TopBarWithEditorPanelContainer
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.BottomSheetContainer
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.BottomSheetEditorState
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet.MultipleBottomSheetContent
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi
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
private const val KEYBOARD_DELAY = 500L

internal typealias SendMessage = (Msg) -> Unit

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: () -> Boolean = { false },
    collapseFabWidget: () -> Unit = {},
    isVisibleOnFabDraftIndicator: MutableState<Boolean> = mutableStateOf(false),
    router: EditNoteRouter? = null,
    sandbox: EditNoteSandbox = koinViewModel()
) {
    sandbox.send(Msg.Inner.FetchedFabStateValue(isExpandedFab()))

    val model = sandbox.model.collectAsStateWithLifecycle().value
    val focusManager = LocalFocusManager.current
    val (titleFocus, messageFocus) = remember { FocusRequester.createRefs() }

    HandleEffects(
        effects = sandbox.effects,
        router = router,
        collapseFabWidget = collapseFabWidget,
        focusManager = focusManager,
        titleFocus = titleFocus,
        isVisibleOnFabDraftIndicator = isVisibleOnFabDraftIndicator,
    )

    Content(
        model = model,
        send = sandbox::send,
        isVisibleOnFabDraftIndicator = isVisibleOnFabDraftIndicator,
        focusManager = focusManager,
        titleFocus = titleFocus,
        messageFocus = messageFocus,
        modifier = modifier
    )
}

@Composable
fun Content(
    model: Model,
    send: SendMessage,
    isVisibleOnFabDraftIndicator: MutableState<Boolean>,
    focusManager: FocusManager,
    titleFocus: FocusRequester,
    messageFocus: FocusRequester,
    wallpaperSelectorApi: NoteWallpaperSelectorApi = get(),
    modifier: Modifier,
) {
    val scrollState = rememberLazyListState()
    val isScrollUp = scrollState.isScrollUp()

    val fetchedWallpaperResId = wallpaperSelectorApi.currentWallpaper.collectAsState()
    val maxLines = 500

    LaunchedEffect(Unit) {
        if (model.isNewNote && model.editorSheet.state != BottomSheetEditorState.EXPANDED) {
            send(Msg.Inner.ShowKeyboardWithFocusOnTitle)
        }
    }

    SideEffect {
        send(Msg.Inner.UpdatedFabIcon(isVisibleOnFabDraftIndicator))
    }

    LaunchedEffect(fetchedWallpaperResId.value) {
        send(Msg.Inner.UpdatedNoteWallpaper(fetchedWallpaperResId.value))
    }

    BackHandler(model.isNewNote) {
        focusManager.clearFocus()
        send(Msg.Ui.OnTopBarBackPressed)
    }

    BoxWithConstraints(
        modifier
            .fillMaxSize()
            .background(background)
    ) {
        val maxHeight = this.maxHeight

        ScreenBackground(model.currentNote.backgroundId)

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
                    title = model.currentNote.title,
                    updateTitle = { titleField -> send(Msg.Inner.UpdatedInputTitle(titleField)) },
                    focusRequester = titleFocus,
                    focusManager = focusManager,
                    maxLines = maxLines,
                    modifier.heightIn(max = maxHeight)
                )
            }

            item {
                NoteMessageInputFieldWidget(
                    message = model.currentNote.message,
                    updateMessage = { msgField -> send(Msg.Inner.UpdatedInputMessage(msgField)) },
                    focusRequester = messageFocus,
                    maxLines = maxLines,
                    modifier.heightIn(max = maxHeight)
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
            currentNote = model.currentNote,
            editorPanelState = model.editorPanelState,
            onTopBarBackPressed = { send(Msg.Ui.OnTopBarBackPressed) },
            isVisibleEditorPanel = isScrollUp,
            modifier = modifier
        )

        BottomSheetContainer(sheetState = model.editorSheet.state) {

            MultipleBottomSheetContent(
                send = send,
                sheetState = model.editorSheet.state,
                currentSheetContent = model.editorSheet.currentContent,
                wallpaperSelector = wallpaperSelectorApi,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun ScreenBackground(
    backgroundResId: Int,
    modifier: Modifier = Modifier
) {
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
private fun HandleEffects(
    effects: Flow<Eff>,
    router: EditNoteRouter?,
    collapseFabWidget: () -> Unit,
    isVisibleOnFabDraftIndicator: MutableState<Boolean>,
    focusManager: FocusManager,
    titleFocus: FocusRequester,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val noteMaxLengthWarning = text.editNote.noteMaxLengthWarning
    val scope = rememberCoroutineScope()

    HandleEffectsWithLifecycle(effects) { eff ->

        when (eff) {
            is Eff.SetInitialExpandedState -> {
                scope.launch {
                    keyboardController?.hide()
                    delay(KEYBOARD_DELAY)
                    titleFocus.requestFocus()
                    keyboardController?.show()
                }
            }
            is Eff.NavigateBack -> router?.let { it.onBack() }
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning)
            is Eff.HideSystemKeyboard -> {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
            is Eff.CollapseFab -> collapseFabWidget()
            is Eff.ResetFabDraftIconState -> isVisibleOnFabDraftIndicator.value = false
        }
    }
}