package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesCollection
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 22.02.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ExpandedSearchQueryFiled(
    model: Model,
    send: SendMessage,
    notesList: NotesListApi.Ui,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { focusRequester.freeFocus() }
    }
    Column(modifier.noRippleClickable {  }) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = dp4, end = dp16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconAction(
                icon = { AppIcon.ArrowBack },
                action = { send(Msg.Ui.OnCollapseSearchBarClicked) }
            )
            TextField(
                value = model.searchQuery,
                onValueChange = { send(Msg.Inner.AfterUserInputQueryChanged(it)) },
                singleLine = true,
                textStyle = TextDesign.body,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = onPrimaryContainer,
                    containerColor = transparent,
                    cursorColor = primary,
                    focusedIndicatorColor = onPrimaryContainer,
                    unfocusedIndicatorColor = secondary,
                    disabledIndicatorColor = secondaryContainer,
                    selectionColors = TextSelectionColors(handleColor = primary, tertiaryContainer),
                ),
                trailingIcon = {
                    AnimateFadeInOut(model.searchQuery.isNotEmpty()) {
                        IconAction(icon = { AppIcon.Close }) {
                            send(Msg.Ui.OnClearInputQueryClicked)
                        }
                    }
                },
                modifier = modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
            )
        }
        SearchListResult(notes = model.searchList, notesApi = notesList, modifier.weight(1f))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchListResult(notes: NotesCollection, notesApi: NotesListApi.Ui, modifier: Modifier) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .systemBarsPadding()
            .padding(top = dp8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = notes.data, key = { note -> note.id }) { note ->
            notesApi.NoteItem(
                onNoteClicked = {},
                onNoteLongClicked = { },
                note = note,
                modifier = Modifier.animateItemPlacement()
            )
        }
        item {
            Spacer(modifier.height(dp8))
        }
    }
}
