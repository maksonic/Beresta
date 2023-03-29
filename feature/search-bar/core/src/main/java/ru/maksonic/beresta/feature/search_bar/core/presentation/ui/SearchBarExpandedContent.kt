package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.feature.notes_list.api.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarExpandedContent(
    model: Model,
    send: SendMessage,
    notesListFeature: NotesListApi.Ui,
    modifier: Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxSize()
            .background(background)
            .noRippleClickable { }
    ) {
        val systemBarsColor = background

        SystemStatusBar(backgroundColor = { systemBarsColor })
        TopBar(model = model, send = send)
        SearchListResult(notes = model.searchList, notesApi = notesListFeature, modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { systemBarsColor })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    model: Model,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .padding(start = dp8, end = dp16),
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
            textStyle = TextDesign.bodyPrimary,
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor = onBackground,
                unfocusedTextColor = onBackground,
                containerColor = transparent,
                cursorColor = primary,
                focusedIndicatorColor = outline,
                unfocusedIndicatorColor = outline,
                disabledIndicatorColor = outline,
                selectionColors = TextSelectionColors(handleColor = primary, onSurfaceVariant),
            ),
            trailingIcon = {
                AnimateFadeInOut(model.searchQuery.isNotEmpty()) {
                    IconAction(icon = { AppIcon.Close }) {
                        send(Msg.Ui.OnClearInputQueryClicked)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = modifier
                .weight(1f)
                .focusRequester(focusRequester)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchListResult(
    notes: NoteUi.Collection,
    notesApi: NotesListApi.Ui,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier.padding(top = dp8),
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
            Spacer(Modifier.height(dp12))
        }
    }
}