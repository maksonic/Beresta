package ru.maksonic.beresta.screen.hidden_notes.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.screen.hidden_notes.core.Model
import ru.maksonic.beresta.screen.hidden_notes.core.Msg
import ru.maksonic.beresta.screen.hidden_notes.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.SnackBar
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 21.07.2023
 */
@Composable
internal fun NotesList(
    model: State<Model>,
    send: SendMessage,
    api: NotesApi.List.Ui,
    chipsRowOffsetHeightPx: State<Float>,
    updateChipsRowOffsetHeight: (Float) -> Unit,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        val sorter = rememberUpdatedState(
            NotesSorter(
                list = model.value.notes.collection.data,
                order = listUiSortState.hiddenNotes.order,
                isSortPinned = listUiSortState.hiddenNotes.isSortPinned,
                sort = listUiSortState.hiddenNotes.sort,
                currentFolderId = 1
            )
        )


        val bottomBarHeight = Theme.widgetSize.bottomBarNormalHeight
        val idle = bottomBarHeight + dp12 + SystemNavigationBarHeight
        val selection = bottomBarHeight + SystemNavigationBarHeight
        val bottomContentPadding = animateDp(if (model.value.notes.isSelection) selection else idle)
        val bottomSnackHostPadding = animateDp(
            if (model.value.notes.isSelection) Theme.widgetSize.bottomBarNormalHeight
            else Theme.widgetSize.bottomMainBarHeight
        )

        api.Widget(
            modifier = modifier.padding(top = Theme.widgetSize.topBarSmallHeight),
            placeholderModifier = Modifier
                .systemBarsPadding()
                .padding(
                    top = Theme.widgetSize.topBarSmallHeight.plus(dp12),
                    start = dp10,
                    end = dp10,
                ),
            state = model.value.notes,
            sorter = sorter,
            onNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
            onNoteLongClicked = { send(Msg.Ui.OnNoteLongClicked(it)) },
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
            updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
            contentPaddingValues = PaddingValues(
                top = dp16,
                start = dp10,
                end = dp10,
                bottom = bottomContentPadding.value
            )
        )

        SnackbarHost(
            hostState = model.value.snackNotesState,
            snackbar = { SnackBar(model.value.snackNotesState.currentSnackbarData) },
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = bottomSnackHostPadding.value)
        )
    }
}
