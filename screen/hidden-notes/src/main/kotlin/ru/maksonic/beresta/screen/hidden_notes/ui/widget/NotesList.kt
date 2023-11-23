package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBar
import ru.maksonic.beresta.common.ui_kit.bar.snackbar.SnackBarHost
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp10
import ru.maksonic.beresta.common.ui_theme.provide.dp12
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.listHiddenNotesSortState
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.WallpaperPickerUiApi
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.core.rememberHiddenNotesSorter
import ru.maksonic.beresta.screen.hidden_notes.ui.Send

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun NotesList(
    model: Model,
    send: Send,
    api: NotesListUiApi,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    wallpaperUiApi: WallpaperPickerUiApi.Wallpaper = koinInject(),

    ) {
    val bottomSnackHostPadding = animateDpAsState(
        if (model.notes.isSelection) Theme.size.bottomMainBarHeight
        else Theme.size.bottomMainBarHeight,
        tween(Theme.animVelocity.common), label = ""

    )
    val sorter = rememberHiddenNotesSorter(model.notes.collection)

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

        api.ListPrimary(
            state = model.notes,
            sorter = sorter,
            gridCells = listHiddenNotesSortState.gridCount,
            onNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
            onNoteLongClicked = { send(Msg.Ui.OnNoteLongClicked(it)) },
            updateScrollUpValue = {},
            updateCanScrollBackwardValue = updatedCanScrollBackwardValue,
            chipsRowOffset = remember { mutableFloatStateOf(0f) },
            updateChipsOffset = {},
            contentPadding = PaddingValues(
                top = dp12,
                start = dp10,
                end = dp10,
                bottom = Theme.size.bottomMainBarHeight + SystemNavigationBarHeight
            ),
            modifier = modifier
                .statusBarsPadding()
                .padding(top = Theme.size.topBarSmallHeight),
            cardBackground = {
                wallpaperUiApi.Widget(
                    it,
                    isCardContainer = true,
                    Modifier.matchParentSize()
                )
            }
        )

        SnackBarHost(
            hostState = model.snackNotesState,
            snackbar = { SnackBar(model.snackNotesState.currentSnackBarData) },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = bottomSnackHostPadding.value)
        )
    }
}
