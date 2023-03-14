package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable
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
    sandbox.sendMsg(Msg.Inner.FetchedFabStateValue(isExpandedFab()))

    val model = sandbox.model.collectAsStateWithLifecycle().value
    val focusManager = LocalFocusManager.current
    val (titleFocus, messageFocus) = remember { FocusRequester.createRefs() }

    HandleEffects(
        effects = sandbox.effects,
        router = router,
        collapseFabWidget = collapseFabWidget,
        focusManager = focusManager,
        titleFocus = titleFocus,
    )

    Content(
        model = model,
        send = sandbox::sendMsg,
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
    val fetchedWallpaperResId = wallpaperSelectorApi.currentWallpaper.collectAsState()
    val scrollState = rememberScrollState()
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                send(Msg.Inner.UpdatedEditorPanelVisibility(isVisible = available.y > 0f))
                return Offset.Zero
            }
        }
    }

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

    Box(
        modifier
            .fillMaxSize()
            .background(background)
            .noRippleClickable { }
    ) {
        ScreenBackground(model.currentNote.backgroundId)

        Column(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)
        ) {
            Spacer(
                modifier
                    .statusBarsPadding()
                    .height(Theme.widgetSize.topBarNormalHeight)
            )

            NoteTitleInputFieldWidget(
                inputValue = model.titleField,
                updateTitle = { titleField -> send(Msg.Inner.UpdatedInputTitle(titleField)) },
                focusRequester = titleFocus,
                focusManager = focusManager,
            )
            NoteMessageInputFieldWidget(
                inputValue = model.messageField,
                updateMessage = { msgField -> send(Msg.Inner.UpdatedInputMessage(msgField)) },
                focusRequester = messageFocus,
            )

            Spacer(
                modifier
                    .statusBarsPadding()
                    .height(Theme.widgetSize.topBarNormalHeight)
            )
        }

        TopBarWithEditorPanelContainer(
            send = send,
            editorPanelState = model.editorPanelState,
            onTopBarBackPressed = { send(Msg.Ui.OnTopBarBackPressed) },
            isVisibleEditorPanel = model.isVisibleEditorPanel,
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
                    titleFocus.requestFocus()
                    keyboardController?.hide()
                    delay(KEYBOARD_DELAY)
                    keyboardController?.show()
                }
            }
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
            is Eff.ShowToastMaxLengthNoteExceed -> context.toastLongTime(noteMaxLengthWarning)
            is Eff.HideSystemKeyboard -> {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
            is Eff.CollapseFab -> collapseFabWidget()
        }
    }
}