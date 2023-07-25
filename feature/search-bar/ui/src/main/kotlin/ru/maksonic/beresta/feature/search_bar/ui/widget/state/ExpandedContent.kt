package ru.maksonic.beresta.feature.search_bar.ui.widget.state

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.android.awaitFrame
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.ui.SearchBarViewModel
import ru.maksonic.beresta.feature.search_bar.ui.widget.EmptySearchResult
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp10
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun ExpandedContent(
    uiState: State<SearchBarUiState>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchBarViewModel = koinViewModel(),
    notesListFeatureApi: NotesApi.Ui.Card = koinInject()
) {
    val searchQuery = viewModel.textFiledValue.collectAsStateWithLifecycle()
    val searchList = remember { mutableStateOf(uiState.value.searchList) }
    val isEmptySearchResult = rememberSaveable { mutableStateOf(false) }
    val isColoredStatusBar = rememberSaveable { mutableStateOf(false) }

    BackHandler(uiState.value.barState != SearchBarState.Collapsed) {
        actions[SearchBarApi.ActionKey.OnCollapseBar]?.invoke()
    }

    LaunchedEffect(searchQuery.value.text) {
        searchList.value = if (searchQuery.value.text.isEmpty()) {
            searchList.value.copy(emptyList())
        } else {
            uiState.value.searchList.copy(uiState.value.searchList.data.filter { note ->
                searchNoteBy(searchQuery.value.text, note)
            })
        }
        isEmptySearchResult.value =
            searchList.value.data.isEmpty().and(searchQuery.value.text.isNotBlank())
    }

    LaunchedEffect(uiState.value.barState) {
        if (uiState.value.barState.isCollapsed) viewModel.updateQuery(TextFieldValue())
    }

    Column(modifier.fillMaxSize()) {
        InputQueryTextFiled(
            searchQuery = searchQuery,
            actions = actions,
            updateQuery = viewModel::updateQuery,
            isColoredBar = isColoredStatusBar
        )
        SearchListResult(
            notes = searchList.value,
            noteCard = notesListFeatureApi,
            isEmptySearch = isEmptySearchResult.value,
            onSearchResultNoteClicked = onSearchResultNoteClicked,
            updateStatusBarColor = { isColoredStatusBar.value = it }
        )
    }
}

@Composable
private fun InputQueryTextFiled(
    searchQuery: State<TextFieldValue>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    updateQuery: (TextFieldValue) -> Unit,
    isColoredBar: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val tonal = animateDp(if (isColoredBar.value) Theme.tonal.Level2 else Theme.tonal.Level0)

    LaunchedEffect(Unit) {
        awaitFrame()
        focusRequester.requestFocus()
    }

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = modifier.fillMaxWidth(),
    ) {
        Box(
            modifier.statusBarsPadding().height(Theme.widgetSize.topBarSmallHeight),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier
                    .fillMaxWidth()
                    .padding(start = dp4, end = dp16),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var inputFieldHaveFocus by remember { mutableStateOf(false) }

                ClickableIcon(
                    icon = AppIcon.ArrowBack,
                    action = { actions[SearchBarApi.ActionKey.OnCollapseBar]?.invoke() },
                )

                TextField(
                    value = searchQuery.value,
                    onValueChange = { updateQuery(it) },
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
                        AnimateFadeInOut(searchQuery.value.text.isNotEmpty()) {
                            ClickableIcon(icon = AppIcon.Close) { updateQuery(TextFieldValue()) }
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
    noteCard: NotesApi.Ui.Card,
    isEmptySearch: Boolean,
    onSearchResultNoteClicked: (Long) -> Unit,
    updateStatusBarColor: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LaunchedEffect(scrollState.canScrollBackward) {
        updateStatusBarColor(scrollState.canScrollBackward)
    }

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(start = dp10, end = dp10, top = dp12, bottom = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = notes.data, key = { note -> note.id }) { note ->
            noteCard.Widget(
                note = note,
                isSelected = false,
                onNoteClicked = onSearchResultNoteClicked,
                onNoteLongClicked = onSearchResultNoteClicked,
                modifier = modifier.animateItemPlacement()
            )
        }
    }

    if (isEmptySearch) {
        EmptySearchResult(modifier.imePadding())
    }
}

private fun searchNoteBy(query: String, note: NoteUi): Boolean {
    val ignoreCase = true
    return when {
        note.title.trim().contains(query.trim(), ignoreCase) -> true
        note.message.trim().contains(query.trim(), ignoreCase) -> true
        else -> false
    }
}
