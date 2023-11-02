package ru.maksonic.beresta.feature.ui.search_bar.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

/**
 * @Author maksonic on 21.02.2023
 */
interface SearchBarUiApi {
    enum class ActionKey {
        OnExpandBar, OnCollapseBar,
        OnCancelClicked, OnShareClicked, OnSelectAllClicked, OnChangeGridClicked,
        OnUserAvatarClicked, OnBackClicked, OnSortByClicked
    }

    @Composable
    fun Widget(
        state: SearchBarUiState,
        isColoredBackplate: State<Boolean>,
        actions: Map<ActionKey, () -> Unit>,
        onSearchResultNoteClicked: (Long) -> Unit,
    )
}