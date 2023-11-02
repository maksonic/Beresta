package ru.maksonic.beresta.feature.ui.edit_note.core.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.delay
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.folders_list.ui.api.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.feature.ui.edit_note.core.LocalAppEditorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.provideEditorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.TopControlBar
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.TopWithBottomBars
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.getWallpaperBackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi

/**
 * @Author maksonic on 16.10.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentExpanded(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier,
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    addFolderDialogApi: AddFolderDialogUiApi,
    markerColorPickerUiApi: MarkerPickerUiApi = koinInject(),
    wallpaperPickerUiApi: WallpaperPickerUiApi = koinInject(),
    wallpaper: WallpaperPickerUiApi.Wallpaper = koinInject(),
    isHiddenNote: Boolean,
    modalBottomSheetState: SheetState,
    focusRequester: FocusRequester,
) {
    CompositionLocalProvider(
        LocalCurrentSelectedFolderState provides currentFolderStoreUiApi.id.value
    ) {
        val currentFolder = FoldersFeature.currentSelected
        val animDelay = Theme.animVelocity.common
        var isAnimated by remember { mutableStateOf(false) }
        val animVelocity = if (isAnimated) animDelay else 0
        val backgroundColor = getWallpaperBackgroundColor(model.currentWallpaper)
        val defBackground = model.currentWallpaper.getParams().backgroundColorId == 100000L
        val defBackground2 = model.currentWallpaper.getParams().backgroundColorId == 0L
        val isNoneWallpapers = with(model.currentWallpaper) { defBackground || defBackground2 }
        LaunchedEffect(Unit) {
            if (!model.isFetchedNote) {
                send(Msg.Inner.FetchedPassedNote)
            }
        }

        LaunchedEffect(FoldersFeature.currentSelected) {
            send(Msg.Inner.FetchedCurrentFolderId(currentFolder))
        }

        LaunchedEffect(Unit) {
            if (!isAnimated) {
                delay(animDelay.toLong())
                isAnimated = true
            }
        }

        Box(Modifier.fillMaxSize()) {

            wallpaper.Widget(model.currentWallpaper, Modifier.fillMaxSize())

            Box(
                modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                val scrollState = rememberScrollState()
                val isVisibleBottomBar = remember { mutableStateOf(true) }
                val scrollOffset = remember { mutableFloatStateOf(0f) }
                val canScrollBackward = rememberUpdatedState(scrollState.canScrollBackward)
                val nestedScrollConnection = remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset, source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            scrollOffset.floatValue = delta
                            val newOffset = scrollOffset.floatValue + delta

                            isVisibleBottomBar.value = when {
                                !scrollState.canScrollBackward -> true
                                !scrollState.canScrollForward -> true
                                else -> newOffset > scrollOffset.floatValue
                            }

                            return Offset.Zero
                        }
                    }
                }


                CompositionLocalProvider(
                    LocalAppEditorColors provides provideEditorColors(
                        isDarkAppTheme = model.currentWallpaper.isDark,
                        isNoneWallpapers = isNoneWallpapers
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(nestedScrollConnection)
                            .verticalScroll(scrollState)
                    ) {
                        TopControlBar(
                            isHiddenNote = isHiddenNote,
                            model = model,
                            send = send,
                            backgroundColor = backgroundColor
                        )

                        NoteTitleInputFieldWidget(model.editableNote.title, send, focusRequester)

                        NoteMessageInputFieldWidget(model.editableNote.message, send)
                    }

                    TopWithBottomBars(
                        model = model,
                        send = send,
                        isHiddenNote = isHiddenNote,
                        isVisibleBottomBar = isVisibleBottomBar,
                        canScrollBackward = canScrollBackward,
                        topBarColor = backgroundColor,
                    )
                }

                if (model.modalSheet.isVisible) {
                    ModalBottomSheetContainer(
                        sheetState = modalBottomSheetState,
                        onDismissRequest = { send(Msg.Inner.HiddenModalBottomSheet) },
                    ) {
                        MultipleModalBottomSheetContent(model, send)
                    }
                }

                markerColorPickerUiApi.Widget(
                    state = model.markerState,
                    onUpdateCurrentColorSelectionClicked = {
                        send(Msg.Inner.UpdatedCurrentNoteMarkerColor(it))
                    },
                    hideDialog = { send(Msg.Ui.HiddenMarkerColorPickerDialog) }
                )

                wallpaperPickerUiApi.SheetContent(
                    isVisible = model.isVisibleWallpaperPickerSheet,
                    currentWallpaper = model.currentWallpaper,
                    hideSheet = { send(Msg.Ui.UpdatedWallpaperPickerSheetVisibility(false)) },
                    updateWallpaper = { send(Msg.Inner.UpdatedNoteWallpaper(it)) }
                )

                addFolderDialogApi.Widget(
                    isVisible = model.isVisibleAddFolderDialog,
                    hideDialog = { send(Msg.Ui.OnCloseAddFolderDialogClicked) }
                )
            }
        }
    }
}
