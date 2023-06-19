package ru.maksonic.beresta.feature.search_bar.core.ui.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.core.Model
import ru.maksonic.beresta.feature.search_bar.core.Msg
import ru.maksonic.beresta.feature.search_bar.core.ui.widget.EmptySearchResultWidget
import ru.maksonic.beresta.feature.search_bar.core.ui.widget.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun SearchBarExpandedContent(
    model: Model,
    send: SendMessage,
    notesListFeatureApi: NotesListApi.Ui,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val isEmptySearchResult = rememberUpdatedState(
        model.searchList.data.isEmpty().and(model.searchQuery.isNotBlank())
    )

    BackHandler(model.barState != SearchBarState.Collapsed) {
        send(Msg.Ui.OnCollapseSearchBarClicked)
    }

    SurfacePro(
        color = transparent,
        modifier = modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Column {
            InputQueryTextFiled(model, send, scrollState)
            SearchListResult(
                notes = model.searchList,
                notesApi = notesListFeatureApi,
                scrollState = scrollState,
                isEmptySearch = isEmptySearchResult.value
            )
        }
    }
}

@Composable
private fun InputQueryTextFiled(
    model: Model,
    send: SendMessage,
    scrollState: LazyListState,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val tonalElevation =
        animateDp(if (!scrollState.canScrollBackward) Theme.tonal.Level0 else Theme.tonal.Level2)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    SurfacePro(
        tonalElevation = tonalElevation.value,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier
                .statusBarsPadding()
                .height(Theme.widgetSize.topBarMediumHeight),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(bottom = dp6, start = dp4, end = dp16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var inputFieldHaveFocus by remember { mutableStateOf(false) }

                IconAction(
                    icon = { AppIcon.ArrowBack },
                    action = { send(Msg.Ui.OnCollapseSearchBarClicked) }
                )
                TextField(
                    value = model.searchQuery,
                    onValueChange = { send(Msg.Inner.UpdatedUserInputQueryChanged(it)) },
                    singleLine = true,
                    textStyle = TextDesign.bodyPrimary,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = onBackground,
                        disabledTextColor = onBackground,
                        focusedContainerColor = transparent,
                        unfocusedContainerColor = transparent,
                        errorContainerColor = transparent,
                        disabledContainerColor = transparent,
                        cursorColor = primary,
                        unfocusedTextColor = outline,
                        focusedTrailingIconColor = onBackground,
                        unfocusedTrailingIconColor = outline,
                        focusedIndicatorColor = outline,
                        unfocusedIndicatorColor = outline,
                        disabledIndicatorColor = outline,
                        selectionColors = TextSelectionColors(
                            handleColor = primary,
                            onSurfaceVariant
                        ),
                    ),
                    placeholder = {
                        if (!inputFieldHaveFocus) {
                            Text(
                                text = text.shared.hintFindNote,
                                style = TextDesign.bodyPrimary.copy(outline)
                            )
                        }
                    },
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
                        .onFocusEvent {
                            inputFieldHaveFocus = it.isFocused
                        }
                )
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchListResult(
    notes: NoteUi.Collection,
    notesApi: NotesListApi.Ui,
    scrollState: LazyListState,
    isEmptySearch: Boolean,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(start = dp10, end = dp10, bottom = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: State
        items(items = notes.data, key = { note -> note.id }) { note ->
            notesApi.NoteListItem(
                note = note,
                state = NoteCardUiState.Initial,
                isSelected = false,
                onNoteClicked = {},
                onNoteLongClicked = { },
                isTrashPlacement = false,
                modifier = modifier.animateItemPlacement()
            )
        }
    }

    if (isEmptySearch) {
        EmptySearchResultWidget(modifier.imePadding())
    }
}