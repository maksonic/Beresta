package ru.maksonic.beresta.feature.search_bar.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.api.isExpanded
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp56
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 25.07.2023
 */
@Composable
internal fun Content(
    uiState: State<SearchBarUiState>,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    BackHandler(uiState.value.barState.isExpanded) {
        actions[SearchBarApi.ActionKey.OnCollapseBar]?.invoke()
    }

    BoxWithConstraints(modifier.fillMaxWidth()) {
        val paddingStart = animateDp(if (uiState.value.barState.isExpanded) dp56 else 112.dp)
        val paddingEnd = animateDp(if (uiState.value.barState.isExpanded) dp16 else 112.dp)

        InnerContainer(
            uiState = uiState,
            isColoredBackplate = isColoredBackplate,
            actions = actions,
            maxHeight = this.maxHeight
        )

        AnimateFadeInOut(uiState.value.barState.isExpanded) {
            ExpandedContent(
                uiState = uiState,
                actions = actions,
                onSearchResultNoteClicked = onSearchResultNoteClicked,
                queryModifier = modifier.padding(start = paddingStart.value, end = paddingEnd.value)
            )
        }

        InnerContent(uiState, actions)
    }
}