package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.LocalListSortState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 22.06.2023
 */
@Composable
fun NotesList(
    model: State<Model>,
    send: SendMessage,
    currentFolderId: Long,
    api: NotesApi.Ui.List,
    chipsRowOffsetHeightPx: MutableState<Float>,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        when {
            model.value.notes.state.isLoading -> {
                api.Placeholder(listUiSortState.gridCount)
            }

            model.value.notes.state.successAfterLoading -> {
                api.Widget(
                    state = model.value.notes,
                    currentFolderId = currentFolderId,
                    onNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
                    onNoteLongClicked = { send(Msg.Ui.OnNoteLongClicked(it)) },
                    chipsRowOffsetHeightPx = chipsRowOffsetHeightPx
                )
            }
        }

        AnimatedVisibility(model.value.notes.isVisibleRemovedSnackBar) {
            val paddingBottom = animateDp(
                if (model.value.notes.isSelection) Theme.widgetSize.bottomBarNormalHeight
                else Theme.widgetSize.bottomMainBarHeight, label = ""
            )
            SnackBarAction(
                message = text.shared.hintRemovedNotesCount.plus(
                    " ${model.value.notes.removedList.count()}"
                ),
                actionTitle = text.shared.btnTitleCancel,
                onClick = { send(Msg.Ui.OnSnackUndoRemoveNotesClicked) },
                modifier = modifier
                    .navigationBarsPadding()
                    .padding(bottom = paddingBottom.value)
            )
        }
    }
}

