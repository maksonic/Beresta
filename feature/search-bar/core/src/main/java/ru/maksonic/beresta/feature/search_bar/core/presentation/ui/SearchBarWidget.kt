package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes_list.api.ui.NotesListSharedScrollState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.presentation.Model
import ru.maksonic.beresta.feature.search_bar.core.presentation.Msg
import ru.maksonic.beresta.feature.search_bar.core.presentation.SearchBarSandbox
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.SystemStatusBarHeight

/**
 * @Author maksonic on 21.02.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class SearchBarWidget : SearchBarApi.Ui {
    private val mutableSharedExpandSearchState = MutableStateFlow(false)
    override val sharedExpandSearchState = mutableSharedExpandSearchState.asStateFlow()

    @Composable
    override fun Widget(
        notesCollection: NoteUi.Collection,
        sharedScroll: NotesListSharedScrollState,
    ) {
        Content(
            notesCollection = notesCollection,
            sharedScroll = sharedScroll,
        )
    }

    @Composable
    private fun Content(
        modifier: Modifier = Modifier,
        sandbox: SearchBarSandbox = koinViewModel(),
        notesList: NotesListApi.Ui = get(),
        notesCollection: NoteUi.Collection,
        sharedScroll: NotesListSharedScrollState,
    ) {
        sandbox.send(Msg.Inner.FetchedNotesCollection(notesCollection))
        val model = sandbox.model.collectAsStateWithLifecycle().value

        LaunchedEffect(model.isExpandedBar) {
            mutableSharedExpandSearchState.update { model.isExpandedBar }
        }

        Column {
            SearchBar(
                model = model,
                send = sandbox::send,
                notesListFeature = notesList,
                sharedScroll = sharedScroll,
                modifier = modifier
            )
            Spacer(modifier.weight(1f))
        }
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    model: Model,
    send: SendMessage,
    notesListFeature: NotesListApi.Ui,
    sharedScroll: NotesListSharedScrollState
) {

    BackHandler(model.isExpandedBar) {
        send(Msg.Ui.OnCollapseSearchBarClicked)
    }

    BoxWithConstraints(modifier) {

        val maxHeight = this.maxHeight
        val collapsedEdgePadding = 64.dp
        val initialTopPadding = SystemStatusBarHeight.plus(dp4)
        val paddingTop = animateDpAsState(if (model.isExpandedBar) 0.dp else initialTopPadding)
        val paddingStart = animateDpAsState(if (model.isExpandedBar) 0.dp else collapsedEdgePadding)
        val selectionPadding = if (sharedScroll.isSelectionState()) collapsedEdgePadding else dp16
        val paddingEnd = animateDpAsState(if (model.isExpandedBar) 0.dp else selectionPadding)
        val barCornerRadius = animateIntAsState(if (model.isExpandedBar) 0 else 50, tween())
        val barHeight =
            animateDpAsState(if (model.isExpandedBar) maxHeight else Theme.widgetSize.searchBarCollapsedHeight)
        val searchBarColor = animateColorAsState(
            if (sharedScroll.isVisibleFirstNoteOffset()) surfaceVariant else onSurfaceVariant
        )

        SearchBarCollapsedBackground(
            isSelectionState = sharedScroll.isSelectionState,
            isVisibleFirstNoteOffset = sharedScroll.isVisibleFirstNoteOffset
        )

        Box(
            modifier
                .fillMaxWidth()
                .padding(
                    top = paddingTop.value,
                    start = paddingStart.value,
                    end = paddingEnd.value
                )
                .height(barHeight.value)
                .clip(RoundedCornerShape(barCornerRadius.value))
                .drawBehind { drawRect(searchBarColor.value) }
        ) {
            if (model.isExpandedBar) {
                SearchBarExpandedContent(model, send, notesListFeature)
            } else {
                SearchBarCollapsedContent(modifier) { send(Msg.Ui.OnExpandSearchBarClicked) }
            }
        }
    }
}

