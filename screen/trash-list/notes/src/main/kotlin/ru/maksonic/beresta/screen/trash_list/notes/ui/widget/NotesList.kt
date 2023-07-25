package ru.maksonic.beresta.screen.trash_list.notes.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.LocalNoteCardState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.notes.core.Model
import ru.maksonic.beresta.screen.trash_list.notes.core.Msg
import ru.maksonic.beresta.screen.trash_list.notes.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.RemoveFolder
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.EmptyTrash
import ru.maksonic.beresta.ui.widget.button.QuaternaryButton
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItemOffset
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 12.07.2023
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun NotesList(
    model: State<Model>,
    send: SendMessage,
    notesListUiApi: NotesApi.Ui.List,
    noteCardUiApi: NotesApi.Ui.Card,
    updateFirstVisibleItemOffset: (Boolean) -> Unit,
    modifier: Modifier
) {
    val scrollState = rememberLazyListState()
    val isVisibleFirstItemOffset = scrollState.isVisibleFirstItemOffset()
    val noteCardState = noteCardUiApi.state.state.collectAsStateWithLifecycle()
    val bottomContentPadding = animateDp(
        if (model.value.isSelectionState) Theme.widgetSize.bottomBarNormalHeight.plus(dp4) else dp4
    )

    LaunchedEffect(isVisibleFirstItemOffset.value) {
        updateFirstVisibleItemOffset(isVisibleFirstItemOffset.value)
    }

    CompositionLocalProvider(LocalNoteCardState provides noteCardState.value) {
        BoxWithConstraints {
            val maxHeight = this.maxHeight

            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(
                    start = dp10,
                    end = dp10,
                    bottom = bottomContentPadding.value
                ),
                modifier = modifier
            ) {

                item {
                    QuaternaryButton(
                        isEnabled = !model.value.isSelectionState,
                        title = text.trash.topBarTitleTrashedFolders,
                        prefixIcon = AppIcon.RemoveFolder,
                        modifier = Modifier.padding(start = dp6, end = dp6)
                    ) {
                        send(Msg.Ui.OnTrashedFoldersBtnClicked)
                    }
                }

                when {
                    model.value.base.isLoading -> {
                        item {
                            notesListUiApi.Placeholder(1, Modifier.height(maxHeight))
                        }
                    }

                    model.value.notes.data.isEmpty() -> {
                        item {
                            ScreenPlaceholder(
                                imageVector = AppImage.EmptyTrash,
                                message = text.trash.messageEmptyTrashNotesList,
                                modifier = Modifier.fillParentMaxHeight()
                            )
                        }
                    }

                    else -> {
                        items(model.value.notes.data) { note ->
                            noteCardUiApi.Widget(
                                note = note,
                                isSelected = model.value.selectedList.contains(note),
                                onNoteClicked = { id -> send(Msg.Ui.OnNoteClicked(id)) },
                                onNoteLongClicked = { id -> send(Msg.Ui.OnNoteLongClicked(id)) },
                                modifier = Modifier.animateItemPlacement()
                            )
                        }
                    }
                }
            }
        }
    }
}

