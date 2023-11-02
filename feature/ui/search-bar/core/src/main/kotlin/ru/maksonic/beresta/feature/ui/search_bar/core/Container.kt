package ru.maksonic.beresta.feature.ui.search_bar.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiState
import ru.maksonic.beresta.feature.ui.search_bar.core.widget.SearchBarBackplate

/**
 * @Author maksonic on 25.07.2023
 */
@Composable
internal fun Container(
    state: SearchBarUiState,
    isColoredBackplate: State<Boolean>,
    actions: Map<SearchBarUiApi.ActionKey, () -> Unit>,
    onNoteClicked: (Long) -> Unit,
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        SearchBarBackplate(
            state = state,
            isColoredBackplate = isColoredBackplate,
            actions = actions
        )

        Content(
            state = state,
            isColoredBackplate = isColoredBackplate,
            actions = actions,
            onNoteClicked = onNoteClicked,
        )
    }
}