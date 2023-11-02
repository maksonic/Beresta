package ru.maksonic.beresta.screen.trash.notes.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.placeholder.PlaceholderEmptyState
import ru.maksonic.beresta.common.ui_kit.sheet.ModalBottomSheetContainer
import ru.maksonic.beresta.common.ui_kit.widget.trash_screen.BottomAppBarTrash
import ru.maksonic.beresta.common.ui_kit.widget.trash_screen.TrashModalSheetDeleteDataContent
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp10
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiState
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash.notes.core.Model
import ru.maksonic.beresta.screen.trash.notes.core.Msg
import ru.maksonic.beresta.screen.trash.notes.core.TrashNotesSorter
import ru.maksonic.beresta.screen.trash.notes.ui.widget.DialogAcceptDeleteItems
import ru.maksonic.beresta.screen.trash.notes.ui.widget.TopBar

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Content(
    model: Model,
    send: Send,
    notesListUiApi: NotesListUiApi,
    modalBottomSheetState: SheetState,
    modifier: Modifier = Modifier,
    wallpaperUiApi: WallpaperPickerUiApi.Wallpaper = koinInject()
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val notesState = rememberUpdatedState(
        NotesListUiState.Initial.copy(
            state = model.base,
            collection = model.notes,
            isSelection = model.isSelection
        )
    )
    val sorter = rememberUpdatedState(TrashNotesSorter(model.notes.data))

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Scaffold(
            topBar = { TopBar(model, send, scrollBehavior) },
            containerColor = background,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddings ->
            Box(modifier.padding(paddings), contentAlignment = Alignment.BottomEnd) {
                notesListUiApi.ListSecondary(
                    state = notesState.value,
                    sorter = sorter,
                    onNoteClicked = { id -> send(Msg.Ui.OnNoteClicked(id)) },
                    onNoteLongClicked = { id -> send(Msg.Ui.OnNoteLongClicked(id)) },
                    contentPadding = PaddingValues(
                        start = dp10,
                        end = dp10,
                        top = dp16,
                        bottom = Theme.size.bottomMainBarHeight
                    ),
                    modifier = Modifier,
                    loadingModifier = Modifier.padding(top = dp16),
                    emptyListPlaceholder = {
                        PlaceholderEmptyState(
                            painter = painterResource(Theme.image.imageEmptyTrash),
                            message = text.trash.messageEmptyTrashNotesList
                        )
                    },
                    cardBackground = { wallpaperUiApi.Widget(it, Modifier.matchParentSize()) }
                )
            }
        }

        BottomAppBarTrash(
            isEmptyList = model.notes.data.isEmpty(),
            isSelection = model.isSelection,
            isDisabled = model.notes.data.none { it.isSelected } && model.isSelection,
            onRestoreClicked = { send(Msg.Ui.OnBottomBarRestoreSelectedNotesClicked) },
            onDeleteClicked = { send(Msg.Ui.OnBottomBarDeleteSelectedNotesClicked) },
            onDeleteAllClicked = { send(Msg.Ui.OnDeleteAllNotesClicked) },
        )

        if (model.isVisibleModalSheet) {
            ModalBottomSheetContainer(
                sheetState = modalBottomSheetState,
                onDismissRequest = { send(Msg.Inner.UpdatedModalSheetState(false)) },
            ) {
                TrashModalSheetDeleteDataContent(
                    hideSheet = { send(Msg.Ui.HideModalBottomSheet) },
                    onRestoreClicked = { send(Msg.Ui.OnModalSheetRestoreClicked) },
                    onDeleteClicked = { send(Msg.Ui.OnModalSheetDeleteClicked) },
                )
            }
        }

        DialogAcceptDeleteItems(model, send)
    }
}
