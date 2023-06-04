package ru.maksonic.beresta.feature.search_bar.core.ui.content

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
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
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.core.Model
import ru.maksonic.beresta.feature.search_bar.core.Msg
import ru.maksonic.beresta.feature.search_bar.core.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Close
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun SearchBarExpandedContent(
    model: Model,
    send: SendMessage,
    notesListFeatureApi: NotesListApi.Ui,
    formatter: DateFormatter,
    modifier: Modifier = Modifier
) {

    BackHandler(model.barState != SearchBarState.Collapsed) {
        send(Msg.Ui.OnCollapseSearchBarClicked)
    }

    SurfacePro(
        color = transparent,
        modifier = modifier
            .systemBarsPadding()
            .fillMaxSize()
    ) {
        Column {
            InputQueryTextFiled(model = model, send = send)
            SearchListResult(
                notes = model.searchList,
                notesApi = notesListFeatureApi,
                formatter = formatter
            )
        }
    }
}

@Composable
private fun InputQueryTextFiled(
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
            onValueChange = { send(Msg.Inner.UpdatedUserInputQueryChanged(it)) },
            singleLine = true,
            textStyle = TextDesign.bodyPrimary,
            colors = TextFieldDefaults.colors(
                focusedTextColor = onBackground,
                disabledTextColor = onBackground,
                focusedContainerColor = transparent,
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
    formatter: DateFormatter,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = modifier.padding(top = dp8),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = notes.data, key = { note -> note.id }) { note ->
            notesApi.NoteListItem(
                note = note,
                isSelected = false,
                onNoteClicked = {},
                onNoteLongClicked = { },
                isTrashPlacement = false,
                currentAppLang = AppLanguage.ENGLISH,
                formatter = formatter,
                modifier = Modifier.animateItemPlacement()
            )
        }
        item {
            Spacer(Modifier.height(dp12))
        }
    }
}