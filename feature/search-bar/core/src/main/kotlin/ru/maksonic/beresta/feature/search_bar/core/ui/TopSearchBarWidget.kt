package ru.maksonic.beresta.feature.search_bar.core.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.TopSearchBarSharedUiState
import ru.maksonic.beresta.feature.search_bar.core.Model
import ru.maksonic.beresta.feature.search_bar.core.Msg
import ru.maksonic.beresta.feature.search_bar.core.SearchBarSandbox
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarCollapsedContent
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarExpandedContent
import ru.maksonic.beresta.feature.search_bar.core.ui.content.SearchBarSelectedContent
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent

/**
 * @Author maksonic on 26.04.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class TopSearchBarWidget : SearchBarApi.Ui {
    override val searchBarSharedUiState = TopSearchBarSharedUiState.Initial

    @Composable
    override fun Widget() {
        SearchBarContainer()
    }

    @Composable
    private fun SearchBarContainer(
        sandbox: SearchBarSandbox = koinViewModel(),
        notesListFeatureApi: NotesListApi.Ui = get(),
        topBarCounterFeatureApi: TopBarCounterApi.Ui = get(),
        modifier: Modifier = Modifier
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()
        val notesListSharedUiState = notesListFeatureApi.sharedUiState.state.collectAsState()
        val searchSharedUiState = searchBarSharedUiState.state.collectAsStateWithLifecycle()

        LaunchedEffect(searchSharedUiState.value.state) {
            sandbox.send(Msg.Inner.UpdatedSearchBarState(searchSharedUiState.value.state))
        }

        LaunchedEffect(model.value.barState) {
            searchBarSharedUiState.update { it.copy(state = model.value.barState) }
        }

        Box(modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            UserAvatarLayer(
                isSelectedState = notesListSharedUiState.value.isSelectionState,
                notesListSharedUiState.value.isVisibleFirstNoteOffset
            )
            ExpandableSearchBarLayer(
                model = model.value,
                send = sandbox::send,
                isVisibleFirstNoteOffset = notesListSharedUiState.value.isVisibleFirstNoteOffset,
                notesListFeatureApi = notesListFeatureApi,
                counterApi = topBarCounterFeatureApi,
            )
        }
    }
}

@Composable
private fun ExpandableSearchBarLayer(
    model: Model,
    send: SendMessage,
    isVisibleFirstNoteOffset: Boolean,
    notesListFeatureApi: NotesListApi.Ui,
    counterApi: TopBarCounterApi.Ui,
    modifier: Modifier = Modifier
) {
    val barTransformAnimSpeed = Theme.animSpeed.searchBarTransform
    val initialTopPadding = SystemStatusBarHeight.plus(dp4)
    val paddingTop = animateDpAsState(
        when (model.barState) {
            SearchBarState.Collapsed -> initialTopPadding
            SearchBarState.Expanded -> 0.dp
            SearchBarState.Selected -> 0.dp
        }, tween(barTransformAnimSpeed), label = ""
    )
    val paddingStart = animateDpAsState(
        when (model.barState) {
            SearchBarState.Collapsed -> 68.dp
            SearchBarState.Expanded -> 0.dp
            SearchBarState.Selected -> 0.dp
        }, tween(barTransformAnimSpeed), label = ""
    )
    val paddingEnd = animateDpAsState(
        when (model.barState) {
            SearchBarState.Collapsed -> dp16
            SearchBarState.Expanded -> 0.dp
            SearchBarState.Selected -> 0.dp
        }, tween(barTransformAnimSpeed), label = ""
    )
    val cornerRadius = animateIntAsState(
        when (model.barState) {
            SearchBarState.Collapsed -> 50
            SearchBarState.Expanded -> 0
            SearchBarState.Selected -> 0
        }, tween(barTransformAnimSpeed), label = ""
    )
    val selectedContainerHeight = Theme.widgetSize.topBarNormalHeight.plus(SystemStatusBarHeight)

    val tonal = animateDpAsState(
        if (isVisibleFirstNoteOffset) Theme.tonal.Level0 else Theme.tonal.Level4,
        label = "",
        animationSpec = tween(Theme.animSpeed.common)
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val barHeight = animateDpAsState(
            when (model.barState) {
                SearchBarState.Collapsed -> Theme.widgetSize.searchBarCollapsedHeight
                SearchBarState.Expanded -> maxHeight
                SearchBarState.Selected -> selectedContainerHeight
            }, tween(barTransformAnimSpeed), label = ""
        )

        SurfacePro(
            tonalElevation = tonal.value,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = paddingTop.value,
                    start = paddingStart.value,
                    end = paddingEnd.value
                )
                .height(barHeight.value)
                .clip(RoundedCornerShape(cornerRadius.value))
        ) { color ->
            val tween = when (model.barState) {
                SearchBarState.Selected -> Theme.animSpeed.disabled
                else -> Theme.animSpeed.common
            }
            val color = animateColorAsState(
                when (model.barState) {
                    SearchBarState.Collapsed -> {
                        if (isVisibleFirstNoteOffset) surfaceVariant else onSurfaceVariant
                    }

                    SearchBarState.Expanded -> background
                    SearchBarState.Selected -> color
                }, label = "", animationSpec = tween(tween)
            )

            Box(modifier.drawBehind { drawRect(color.value) }) {
                AnimateContent(state = model.barState) { state ->
                    when (state) {
                        SearchBarState.Collapsed -> {
                            SearchBarCollapsedContent(
                                onExpandClicked = { send(Msg.Ui.OnExpandSearchBarClicked) }
                            )
                        }

                        SearchBarState.Expanded -> {
                            SearchBarExpandedContent(model, send, notesListFeatureApi)
                        }

                        SearchBarState.Selected -> SearchBarSelectedContent(counterApi)
                    }
                }
            }
        }
    }
}

