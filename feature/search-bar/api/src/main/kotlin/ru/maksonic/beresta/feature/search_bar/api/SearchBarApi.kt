package ru.maksonic.beresta.feature.search_bar.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarApi {
    enum class ActionKey {
        OnExpandBar, OnCollapseBar,
        OnCancelClicked, OnShareClicked, OnSelectAllClicked, OnChangeGridClicked,
        OnUserAvatarClicked, OnBackClicked, OnSortByClicked
    }

    interface Ui {
        @Composable
        fun Widget(
            state: State<SearchBarUiState>,
            isColoredBackplate: State<Boolean>,
            actions: Map<ActionKey, () -> Unit>,
            onSearchResultNoteClicked: (Long) -> Unit,
        )
    }
}