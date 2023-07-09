package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {
    enum class ActionKey {
        OnExpandBar, OnCollapseBar,
        OnCancelClicked, OnShareClicked, OnSelectAllClicked, OnChangeGridClicked
    }

    interface Ui {
        @Composable
        fun Widget(
            state: State<SearchBarUiState>,
            actions: Map<ActionKey, () -> Unit>,
            onSearchResultNoteClicked: (Long) -> Unit,
        )
    }
}