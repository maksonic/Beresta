package ru.maksonic.beresta.screen.main.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.folders_chips.api.ui.ChipFeature
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NotesSorter
import ru.maksonic.beresta.feature.sorting_sheet.api.listUiSortState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.main.core.Model
import ru.maksonic.beresta.screen.main.core.Msg
import ru.maksonic.beresta.screen.main.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.bar.SnackBarAction
import ru.maksonic.beresta.ui.widget.bar.system.SystemNavigationBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 22.06.2023
 */
@Composable
internal fun NotesList(
    model: State<Model>,
    send: SendMessage,
    api: NotesApi.Ui.List,
    chipsRowOffsetHeightPx: State<Float>,
    updateChipsRowOffsetHeight: (Float) -> Unit,
    updatedCanScrollBackwardValue: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        val padding = with(Theme.widgetSize) { topBarSmallHeight.plus(noteChipsContainerHeight) }

        val idleBottomPadding = Theme.widgetSize.bottomMainBarHeight + SystemNavigationBarHeight
        val selectionPadding = Theme.widgetSize.bottomBarNormalHeight + SystemNavigationBarHeight
        val bottomContentPadding =
            animateDp(if (model.value.notes.isSelection) selectionPadding else idleBottomPadding)

        val sorter = rememberUpdatedState(
            NotesSorter(
                list = model.value.notes.collection.data,
                order = listUiSortState.notes.order,
                isSortPinned = listUiSortState.notes.isSortPinned,
                sort = listUiSortState.notes.sort,
                currentFolderId = ChipFeature.currentSelectedFolder
            )
        )

        api.Widget(
            modifier = modifier.padding(top = Theme.widgetSize.topBarSmallHeight),
            placeholderModifier = Modifier
                .systemBarsPadding()
                .padding(top = padding.plus(dp4)),
            state = model.value.notes,
            sorter = sorter,
            onNoteClicked = { send(Msg.Ui.OnNoteClicked(it)) },
            onNoteLongClicked = { send(Msg.Ui.OnNoteLongClicked(it)) },
            chipsRowOffsetHeightPx = chipsRowOffsetHeightPx,
            updateChipsRowOffsetHeight = updateChipsRowOffsetHeight,
            updatedCanScrollBackwardValue = updatedCanScrollBackwardValue,
            contentPaddingValues = PaddingValues(
                top = Theme.widgetSize.noteChipsContainerHeight.plus(dp4),
                start = dp10,
                end = dp10,
                bottom = bottomContentPadding.value
            )
        )

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

