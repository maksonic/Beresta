package ru.maksonic.beresta.screen.trash_list.notes.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.movableContentOf
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.api.NotesApi
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
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 12.07.2023
 */
@Composable
internal fun NotesList(
    model: State<Model>,
    send: SendMessage,
    notesListUiApi: NotesApi.Ui.List,
    noteCardUiApi: NotesApi.Ui.Card,
    modifier: Modifier
) {
    val trashButton = movableContentOf {
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
                Column(modifier.padding(start = dp10, end = dp10)) {
                    trashButton()

                    notesListUiApi.Placeholder(1, Modifier.weight(1f))
                }
            }

            model.value.notes.data.isEmpty() -> {
                Column(modifier.padding(start = dp10, end = dp10)) {
                    trashButton()

                    ScreenPlaceholder(
                        imageVector = AppImage.EmptyTrash,
                        message = text.trash.messageEmptyTrashNotesList,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            model.value.base.successAfterLoading -> {
                SuccessContent(
                    model = model,
                    send = send,
                    noteCardUiApi = noteCardUiApi,
                    modifier = modifier,
                    trashButton = trashButton
                )
            }

        }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SuccessContent(
    model: State<Model>,
    send: SendMessage,
    noteCardUiApi: NotesApi.Ui.Card,
    modifier: Modifier,
    trashButton: @Composable () -> Unit
) {
    val scrollState = rememberLazyListState()
    val bottomContentPadding = animateDp(
        if (model.value.isSelectionState) Theme.widgetSize.bottomBarNormalHeight.plus(dp4) else dp4
    )

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
            trashButton()
        }

        items(model.value.notes.data, key = { item -> item.id }) { note ->
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
