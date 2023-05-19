package ru.maksonic.beresta.feature.top_bar_counter.api

import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 30.04.2023
 */
data class TopBarCounterSharedUiState(
    val countValue: Int,
    val onCancelClicked: () -> Unit,
    val onSelectAllClicked: () -> Unit,
    val onShareClicked: () -> Unit,
) {
    companion object {
        private val DefaultState = TopBarCounterSharedUiState(
            countValue = 0,
            onCancelClicked = {},
            onSelectAllClicked = {},
            onShareClicked = {}
        )
        val Initial = object : SharedUiState<TopBarCounterSharedUiState>(DefaultState) {}
    }
}

fun SharedUiState<TopBarCounterSharedUiState>.intiClickActions(
    onCancelClicked: () -> Unit,
    onSelectAllClicked: () -> Unit
) = this.update {
    it.copy(
        onCancelClicked = onCancelClicked,
        onSelectAllClicked = onSelectAllClicked
    )
}

fun SharedUiState<TopBarCounterSharedUiState>.updateCounterValue(value: Int) =
    this.update { it.copy(countValue = value) }