package ru.maksonic.beresta.feature.ui.edit_note.core.screen

import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersFeature
import ru.maksonic.beresta.feature.folders_list.ui.api.LocalCurrentSelectedFolderState
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiApi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagsListUiApi
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.AddFolderDialogUiApi
import ru.maksonic.beresta.feature.ui.edit_note.core.LocalAppEditorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.provideEditorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.rememberColorCreator
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.ImagesCarousel
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.MultipleModalBottomSheetContent
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.TopControlBar
import ru.maksonic.beresta.feature.ui.edit_note.core.widget.TopWithBottomBars
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.BaseWallpaper
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperGradient
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperImage
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.platform.core.ui.findActivity

/**
 * @Author maksonic on 16.10.2023
 */
private const val TRANSPARENT_COLOR = android.graphics.Color.TRANSPARENT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ContentExpanded(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier,
    currentFolderStoreUiApi: FoldersChipsRowUiApi.CurrentSelectedFolderStore,
    addFolderDialogApi: AddFolderDialogUiApi,
    markerColorPickerUiApi: MarkerPickerUiApi = koinInject(),
    tagsListUiApi: TagsListUiApi = koinInject(),
    wallpaperPickerUiApi: WallpaperPickerUiApi = koinInject(),
    wallpaper: WallpaperPickerUiApi.Wallpaper = koinInject(),
    isHiddenNote: Boolean,
    modalBottomSheetState: SheetState,
    focusRequester: FocusRequester,
) {
    CompositionLocalProvider(
        LocalCurrentSelectedFolderState provides currentFolderStoreUiApi.id.value
    ) {
        val scrollState = rememberScrollState()
        val isVisibleBars = remember { mutableStateOf(true) }
        val scrollOffset = remember { mutableFloatStateOf(0f) }
        val canScrollBackward = remember { derivedStateOf { scrollState.canScrollBackward } }
        val colorCreator = rememberColorCreator(model.currentWallpaper, canScrollBackward)
        val currentFolder = FoldersFeature.currentSelected
        val isNoneWallpaper = isInitialByCategoryWallpaper(model.currentWallpaper)
        val modalSheetShape = animateDpAsState(
            if (modalBottomSheetState.targetValue == SheetValue.Expanded) 0.dp else dp24, label = ""
        )

        SystemBarColorEffect(model, isNoneWallpaper.value)

        LaunchedEffect(Unit) {
            if (!model.isFetchedNote) {
                send(Msg.Inner.FetchedPassedNote)
            }
        }

        LaunchedEffect(model.isFetchedFolders) {
            if (model.isFetchedFolders) {
                send(Msg.Inner.FetchedCurrentFolderId(currentFolder))
            }
        }

        LaunchedEffect(FoldersFeature.currentSelected) {
            if (model.isFetchedFolders) {
                send(Msg.Inner.FetchedCurrentFolderId(currentFolder))
            }
        }

        Box(Modifier.fillMaxSize()) {
            wallpaper.Widget(
                wallpaper = model.currentWallpaper,
                isCardContainer = false,
                modifier = Modifier.fillMaxSize()
            )

            BoxWithConstraints(modifier.fillMaxSize()) {
                val maxHeight = this.maxHeight

                val nestedScrollConnection = remember {
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset, source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            scrollOffset.floatValue = delta
                            val newOffset = scrollOffset.floatValue + delta

                            isVisibleBars.value = when {
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
                        isNoneWallpaper = isNoneWallpaper.value
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .nestedScroll(nestedScrollConnection)
                            .verticalScroll(scrollState)
                    ) {
                        val messageModifier = if (model.editableNote.images.isNotEmpty())
                            Modifier.padding(bottom = dp8) else
                            Modifier
                                .imePadding()
                                .padding(
                                    bottom = Theme.size.bottomMainBarHeight.plus(
                                        SystemNavigationBarHeight
                                    )
                                )

                        TopControlBar(
                            model = model,
                            send = send,
                            colorCreator = colorCreator,
                            isHiddenNote = isHiddenNote
                        )

                        NoteTitleInputFieldWidget(model.editableNote.title, send, focusRequester)

                        NoteMessageInputFieldWidget(
                            message = model.editableNote.message,
                            send = send,
                            modifier = messageModifier
                        )

                        ImagesCarousel(
                            images = model.editableNote.images,
                            count = if (model.editableNote.images.count() > 9) model.editableNote.images.count() - 9 else model.editableNote.images.count(),
                            onPositionChanged = { from, to ->
                                send(Msg.Ui.UpdateNoteImageInCarouselPosition(from, to))
                            },
                            modifier = Modifier.heightIn(max = maxHeight)
                        )
                    }

                    TopWithBottomBars(
                        model = model,
                        send = send,
                        colorCreator = colorCreator,
                        isVisibleBars = isVisibleBars,
                        isHiddenNote = isHiddenNote
                    )
                }

                if (model.modalSheet.isVisible) {
                    ModalBottomSheetContainer(
                        sheetState = modalBottomSheetState,
                        isEnableDragHandle = false,
                        shape = RoundedCornerShape(
                            topStart = modalSheetShape.value,
                            topEnd = modalSheetShape.value
                        ),
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

                tagsListUiApi.SheetContent(
                    isVisible = model.isVisibleTagPickerSheet,
                    passedNoteTagsIds = model.editableNote.tags.data.map { it.id },
                    updatedNoteTags = { tags -> send(Msg.Inner.UpdatedCurrentNoteTags(tags)) },
                    hideSheet = { send(Msg.Inner.UpdatedTagPickerSheetState(false)) }
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


@Composable
private fun SystemBarColorEffect(model: Model, isNoneWallpaper: Boolean) {
    val activity = LocalContext.current.findActivity()
    val isDarkTheme = Theme.darkMode.value

    DisposableEffect(model.currentWallpaper) {
        activity.enableEdgeToEdge(
            statusBarStyle = setSystemBarColorByWallpaperColor(
                isNoneWallpaper = isNoneWallpaper,
                isDarkWallpaper = model.currentWallpaper.isDark,
                isDarkTheme = isDarkTheme
            ),
            navigationBarStyle = setSystemBarColorByWallpaperColor(
                isNoneWallpaper = isNoneWallpaper,
                isDarkWallpaper = model.currentWallpaper.isDark,
                isDarkTheme = isDarkTheme
            )
        )
        onDispose {
            val navigationBarStyle = if (isDarkTheme) SystemBarStyle.dark(TRANSPARENT_COLOR)
            else SystemBarStyle.light(TRANSPARENT_COLOR, TRANSPARENT_COLOR)

            activity.enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    lightScrim = TRANSPARENT_COLOR,
                    darkScrim = TRANSPARENT_COLOR,
                    detectDarkMode = { isDarkTheme }
                ),
                navigationBarStyle = navigationBarStyle
            )
        }
    }

    LaunchedEffect(model.isVisibleTagPickerSheet) {
        activity.enableEdgeToEdge(
            statusBarStyle = setSystemBarColorByWallpaperColor(
                isNoneWallpaper = isNoneWallpaper,
                isDarkWallpaper = model.currentWallpaper.isDark,
                isDarkTheme = isDarkTheme
            ),
            navigationBarStyle = setSystemBarColorByWallpaperColor(
                isNoneWallpaper = isNoneWallpaper,
                isDarkWallpaper = model.currentWallpaper.isDark,
                isDarkTheme = isDarkTheme
            )
        )
    }
}

@Composable
private fun isInitialByCategoryWallpaper(wallpaper: BaseWallpaper<Color>): State<Boolean> {
    var state by remember { mutableStateOf(true) }

    state = when (wallpaper) {
        is WallpaperColor -> wallpaper.id == 100000L
        is WallpaperGradient -> false
        is WallpaperTexture -> wallpaper.backgroundColor.id == 0L
        is WallpaperImage -> false
        else -> true
    }
    return remember { derivedStateOf { state } }
}

private fun setSystemBarColorByWallpaperColor(
    isNoneWallpaper: Boolean,
    isDarkWallpaper: Boolean,
    isDarkTheme: Boolean
): SystemBarStyle {
    val lightStatusBar = SystemBarStyle.light(TRANSPARENT_COLOR, TRANSPARENT_COLOR)
    val darkStatusBar = SystemBarStyle.dark(TRANSPARENT_COLOR)
    val noneWallpaperSystemBarsColor = if (isDarkTheme) darkStatusBar else lightStatusBar
    val hasWallpaperSystemBarsColor = if (isDarkWallpaper) darkStatusBar else lightStatusBar

    return if (isNoneWallpaper) noneWallpaperSystemBarsColor else hasWallpaperSystemBarsColor

}