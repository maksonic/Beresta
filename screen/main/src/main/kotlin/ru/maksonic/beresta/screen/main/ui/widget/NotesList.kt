package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBar
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHost
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp10
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listNotesSortState
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.core.sorter.rememberNotesSorter
import ru.maksonic.beresta.screen.main.ui.screen.Send

/**
 * @Author maksonic on 22.06.2023
 */
@Composable
internal fun NotesList(
    model: Model,
    send: Send,
    api: NotesListUiApi,
    modifier: Modifier = Modifier,
    wallpaperUiApi: WallpaperPickerUiApi.Wallpaper = koinInject(),
    updateScrollUpValue: (Boolean) -> Unit,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    chipsRowOffset: State<Float>,
    updateChipsRowOffset: (Float) -> Unit,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        val sorter = rememberNotesSorter(model.notes.collection.data)
        val bottomSnackHostPadding = Theme.size.bottomMainBarHeight.plus(dp8)

        api.ListPrimary(
            state = model.notes,
            sorter = sorter,
            gridCells = listNotesSortState.gridCount,
            onNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
            onNoteLongClicked = { send(Msg.Ui.OnNoteLongClicked(it)) },
            updateScrollUpValue = updateScrollUpValue,
            updateCanScrollBackwardValue = updatedCanScrollBackwardValue,
            chipsRowOffset = chipsRowOffset,
            updateChipsOffset = updateChipsRowOffset,
            contentPadding = PaddingValues(
                top = Theme.size.noteChipsContainerHeight.plus(dp8),
                start = dp10,
                end = dp10,
                bottom = Theme.size.bottomMainBarHeight.plus(SystemNavigationBarHeight)
            ),
            modifier = modifier
                .statusBarsPadding()
                .padding(top = Theme.size.topBarSmallHeight),
            cardBackground = {
                wallpaperUiApi.Widget(
                    wallpaper = it,
                    isCardContainer = true,
                    modifier = Modifier.matchParentSize()
                )
            }
        )

        SnackBarHost(
            hostState = model.snackNotesState,
            snackbar = { SnackBar(model.snackNotesState.currentSnackBarData) },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = bottomSnackHostPadding)
        )
    }
}
