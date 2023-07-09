package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarState
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isCollapsed
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.feature.search_bar.api.isSelection
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.widget.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateContent
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 26.04.2023
 */
class TopSearchBarWidget : SearchBarApi.Ui {

    @Composable
    override fun Widget(
        state: State<SearchBarUiState>,
        actions: Map<SearchBarApi.ActionKey, () -> Unit>,
        onSearchResultNoteClicked: (Long) -> Unit,
    ) {
        SearchBarContainer(state, actions, onSearchResultNoteClicked)
    }
}

@Composable
private fun SearchBarContainer(
    state: State<SearchBarUiState>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
    notesListUiApi: NotesApi.Ui.List = koinInject()
) {
    val sharedNotesUiState = notesListUiApi.sharedUiState.state.collectAsStateWithLifecycle()
    val isInitialNotesScroll = rememberUpdatedState(!sharedNotesUiState.value.canScrollBackward)
    val isSelectionState = rememberUpdatedState(state.value.barState.isSelection)

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        SearchBarBackplate(isInitialNotesScroll, isSelectionState)
        SearchBarContent(
            uiState = state,
            actions = actions,
            onSearchResultNoteClicked = onSearchResultNoteClicked,
            isInitialNotesScroll = isInitialNotesScroll,
        )
    }
}

@Composable
private fun SearchBarContent(
    uiState: State<SearchBarUiState>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
    isInitialNotesScroll: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val isCollapsed = rememberUpdatedState(uiState.value.barState.isCollapsed)
    val animSpeed = Theme.animSpeed.searchBarTransform
    val initTopPadding = SystemStatusBarHeight.plus(dp4)
    val paddingTop = animateDp(if (isCollapsed.value) initTopPadding else 0.dp, animSpeed)
    val paddingStart = animateDp(if (isCollapsed.value) 68.dp else 0.dp, animSpeed)
    val paddingEnd = animateDp(if (isCollapsed.value) dp16 else 0.dp, animSpeed)
    val cornerRadius = animateIntAsState(if (isCollapsed.value) 50 else 0, tween(animSpeed))
    val tonal = animateDp(
        if (isInitialNotesScroll.value)
            Theme.tonal.Level0 else Theme.tonal.Level2, Theme.animSpeed.common
    )

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val selectedContainerHeight = Theme.widgetSize.topBarNormalHeight + SystemStatusBarHeight
        val barHeight = animateDp(
            when (uiState.value.barState) {
                SearchBarState.Collapsed -> Theme.widgetSize.searchBarCollapsedHeight
                SearchBarState.Expanded -> maxHeight
                SearchBarState.Selected -> selectedContainerHeight
            }, animSpeed
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
        ) { resultColor ->

            val tween = when (uiState.value.barState) {
                SearchBarState.Selected -> Theme.animSpeed.disabled
                else -> Theme.animSpeed.common
            }

            val color = animateColorAsState(
                when (uiState.value.barState) {
                    SearchBarState.Expanded -> background
                    SearchBarState.Selected -> resultColor
                    SearchBarState.Collapsed -> {
                        if (isInitialNotesScroll.value) surfaceVariant else onSurfaceVariant
                    }
                }, label = "", animationSpec = tween(tween)
            )
            Box(
                modifier
                    .fillMaxWidth()
                    .height(Theme.widgetSize.searchBarCollapsedHeight)
                    .drawBehind { drawRect(color.value) })
        }

        AnimateContent(uiState.value.barState) { state ->
            when {
                state.isExpanded -> {
                    SearchBarExpandedContent(
                        uiState = uiState,
                        actions = actions,
                        onSearchResultNoteClicked = onSearchResultNoteClicked
                    )
                }

                state.isSelection -> SearchBarSelectedContent(actions)
                state.isCollapsed -> {
                    SearchBarCollapsedContent(
                        onExpandClicked = { actions[SearchBarApi.ActionKey.OnExpandBar]?.invoke() },
                        onChangeGridClicked = {
                            actions[SearchBarApi.ActionKey.OnChangeGridClicked]?.invoke()
                        }
                    )
                }
            }
        }
    }
}