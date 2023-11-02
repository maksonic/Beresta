package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp6
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUi
import ru.maksonic.beresta.feature.ui.search_bar.core.widget.SearchListResult
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.ui.search_bar.api.isCollapsed
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun ExpandedContent(
    state: SearchBarUiState,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    onNoteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
    queryModifier: Modifier,
    viewModel: SearchBarViewModel = koinViewModel(),
) {
    val searchQuery = viewModel.textFiledValue.collectAsStateWithLifecycle()
    val searchList = remember { mutableStateOf(state.searchList) }
    val isEmptySearchResult = rememberSaveable { mutableStateOf(false) }
    val isColoredStatusBar = rememberSaveable { mutableStateOf(false) }

    BackHandler(state.barState != SearchBarState.Collapsed) {
        actions[SearchBarUiApi.ActionKey.OnCollapseBar]?.invoke()
    }

    LaunchedEffect(searchQuery.value.text) {
        searchList.value = if (searchQuery.value.text.isEmpty()) {
            searchList.value.copy(emptyList())
        } else {
            state.searchList.copy(state.searchList.data.filter { note ->
                searchNoteBy(searchQuery.value.text, note)
            })
        }
        isEmptySearchResult.value =
            searchList.value.data.isEmpty().and(searchQuery.value.text.isNotBlank())
    }

    LaunchedEffect(state.barState) {
        if (state.barState.isCollapsed) viewModel.updateQuery(TextFieldValue())
    }

    Column(modifier.fillMaxSize()) {
        InputQueryTextFiled(
            searchQuery = searchQuery,
            updateQuery = viewModel::updateQuery,
            isColoredBar = isColoredStatusBar,
            modifier = queryModifier
        )
        SearchListResult(
            notes = searchList.value,
            isEmptySearch = isEmptySearchResult.value,
            onNoteClicked = onNoteClicked,
            updateStatusBarColor = { isColoredStatusBar.value = it }
        )
    }
}

@Composable
private fun InputQueryTextFiled(
    searchQuery: State<TextFieldValue>,
    updateQuery: (TextFieldValue) -> Unit,
    isColoredBar: State<Boolean>,
    modifier: Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val tonal = animateDpAsState(if (isColoredBar.value) Theme.tonal.level2 else Theme.tonal.level0,
        tween(Theme.animVelocity.searchBarTransform), label = ""
    )

    LaunchedEffect(Unit) {
        awaitFrame()
        focusRequester.requestFocus()
    }

    SurfacePro(
        tonalElevation = tonal.value,
        modifier = Modifier.fillMaxWidth(),
    ) {

        var inputFieldHaveFocus by remember { mutableStateOf(false) }

        TextField(
            value = searchQuery.value,
            onValueChange = { updateQuery(it) },
            singleLine = true,
            textStyle = TextDesign.bodyLarge,
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
                    handleColor = primary, onSurfaceVariant
                ),
            ),
            placeholder = {
                if (!inputFieldHaveFocus) {
                    Text(
                        text = text.shared.hintFindNote,
                        style = TextDesign.bodyLarge.copy(outline)
                    )
                }
            },
            trailingIcon = {
                AnimateFadeInOut(searchQuery.value.text.isNotEmpty()) {
                    ButtonIcon(icon = AppIcon.Close) { updateQuery(TextFieldValue()) }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = modifier
                .statusBarsPadding()
                .padding(top = dp4, bottom = dp6)
                .focusRequester(focusRequester)
                .onFocusEvent {
                    inputFieldHaveFocus = it.isFocused
                }
        )
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
