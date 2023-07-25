package ru.maksonic.beresta.feature.search_bar.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.search_bar.ui.widget.SearchBarBackplate

/**
 * @Author maksonic on 25.07.2023
 */
@Composable
internal fun Container(
    state: State<SearchBarUiState>,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarApi.ActionKey, () -> Unit>,
    onSearchResultNoteClicked: (Long) -> Unit,
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        SearchBarBackplate(
            state = state,
            isColoredBackplate = isColoredBackplate,
            actions = actions
        )

        Content(
            uiState = state,
            isColoredBackplate = isColoredBackplate,
            actions = actions,
            onSearchResultNoteClicked = onSearchResultNoteClicked,
        )
    }
}